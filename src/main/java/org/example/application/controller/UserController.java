package org.example.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.application.entity.User;
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
        //TODO Implement login
        return null;
    }

    public Response getUser(Request request) {
        // TODO implement findByUsername
        String username = request.getPath().split("/")[1];
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
        } else if(request.getMethod().getName().equals("GET")) {
            return getUser(request);
        }

        return null;
    }
}
