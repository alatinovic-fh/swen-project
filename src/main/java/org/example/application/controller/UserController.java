package org.example.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.application.entity.User;
import org.example.application.service.UserService;
import org.example.server.http.Request;
import org.example.server.http.Response;
import org.example.server.http.Status;

public class UserController extends Controller {

    private final UserService userService = new UserService();

    private Response register(Request request) {
        //request -> User
        User user = fromBody(request.getBody(), User.class);
        user = userService.create(user);
        return json(Status.CREATED, user);
    }

    public Response login(Request request) {
        //TODO Implement login
        return null;
    }

    public Response getUser(Request request) {
        // TODO implement getUserByUsername
        return null;
    }

    public Response updateUserdata(Request request) {
        return null;
    }

    @Override
    public Response handle(Request request) {
        // TODO Handle logic GET, POST, PUT, DELETE
        if (request.getMethod().getName().equals("POST")) {
            return register(request);
        }

        return null;
    }
}
