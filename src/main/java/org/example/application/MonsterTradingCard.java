package org.example.application;

import org.example.application.controller.UserController;
import org.example.server.Application;
import org.example.server.http.Request;
import org.example.server.http.Response;
import org.example.server.http.Status;

public class MonsterTradingCard implements Application {

    private final UserController userController = new UserController();

    @Override
    public Response handle(Request request) {
        Response response  = new Response();
        switch (request.getPath()) {
            case "/users":
                return userController.register(request);
            default:
                response.setStatus(Status.NOT_FOUND);
                response.setHeader("Content-Type", "text/html");
                response.setBody("<h1>404 NOT FOUND</h1>");
        }

        return response;

    }
}
