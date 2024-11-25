package org.example.application.controller;

import org.example.application.entity.User;
import org.example.application.exception.AuthenticationFailedException;
import org.example.application.exception.UserAlreadyExistsException;
import org.example.application.service.UserService;
import org.example.server.http.Request;
import org.example.server.http.Response;
import org.example.server.http.Status;

public class UserController extends Controller {

    private final UserService userService = new UserService();

    private Response register(Request request) {
        //request -> User
        User user = fromBody(request.getBody(), User.class);
        Response response = new Response();
        try {
            user = userService.create(user);
            response = json(Status.CREATED, user);
        }catch (UserAlreadyExistsException e) {
            response.setStatus(Status.CONFLICT);
            response.setBody(e.getMessage());
        }
        return response;
    }

    public Response login(Request request) {
        Response response = new Response();
        User user = fromBody(request.getBody(), User.class);
        try{
            String token = userService.auth(user);
            response = json(Status.OK, token);
        }catch (AuthenticationFailedException e) {
            response.setStatus(Status.UNAUTHORIZED);
            response.setBody(e.getMessage());
        }
        return response;
    }

    @Override
    public Response handle(Request request) {
        // TODO Handle logic GET, POST, PUT, DELETE
        if (request.getMethod().getName().equals("POST") && request.getPath().startsWith("/user")) {
            return register(request);
        } else if (request.getMethod().getName().equals("POST") && request.getPath().startsWith("/sessions")) {
            return login(request);
        }
        return null;
    }
}
