package org.example.application;

import org.example.application.controller.Controller;
import org.example.application.controller.UserController;
import org.example.application.exception.ControllerNotFoundException;
import org.example.application.routing.Router;
import org.example.server.Application;
import org.example.server.http.Request;
import org.example.server.http.Response;
import org.example.server.http.Status;

public class MonsterTradingCard implements Application {

    private final Router router;

    public MonsterTradingCard(){
        this.router = new Router();
        this.initializeRoutes();
    }

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
