package org.example.application;

import org.example.application.controller.Controller;
import org.example.application.controller.UserController;
import org.example.application.entity.User;
import org.example.application.exception.ControllerNotFoundException;
import org.example.application.routing.Router;
import org.example.server.Application;
import org.example.server.http.Request;
import org.example.server.http.Response;
import org.example.server.http.Status;

/**
 * The MonsterTradingCard class implements the Application interface and represents
 * a simple web application that handles HTTP requests and routes them to appropriate controllers.
 *
 * @author Aleksandar Latinovic
 * */
public class MonsterTradingCard implements Application {

    private final Router router;

    public MonsterTradingCard(){
        this.router = new Router();
        this.initializeRoutes();
    }

    /**
     * Handles an incoming HTTP request and returns an appropriate HTTP response.
     *
     * @param request request The incoming HTTP request
     * @return The HTTP response to be sent back to the client.
     */
 @Override
    public Response handle(Request request) {
        Response response = new Response();
        try{
            Controller controller = this.router.getController(request.getPath());
            response = controller.handle(request);
        }catch (ControllerNotFoundException e){
            response.setStatus(Status.NOT_FOUND);
            response.setHeader("Content-Type", "text/html");
            response.setBody("<h1> " + e.getMessage() +  "</h1>");
        }
        return response;
    }

    private void initializeRoutes(){
        this.router.addRoute("/users", new UserController());
        this.router.addRoute("/sessions", new UserController());
    }
}
