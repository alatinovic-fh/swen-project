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

    public Response register(Request request) {
        //request -> User
        ObjectMapper objectMapper = new ObjectMapper();
        Response response = new Response();
        try {
            User user = objectMapper.readValue(request.getBody(), User.class);
            user = userService.create(user);
            response.setStatus(Status.CREATED);
            response.setHeader("Content-Type", "application/json");
            response.setBody("{ \"Username\": \"" + user.getUsername() + "\" }");
        } catch (JsonProcessingException e) {
            response.setStatus(Status.INTERNAL_SERVER_ERROR);
            response.setBody(e.getMessage());
        }
        return response;
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
