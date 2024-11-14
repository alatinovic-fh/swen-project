package org.example.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.application.entity.User;
import org.example.application.service.UserService;
import org.example.server.http.Request;
import org.example.server.http.Response;
import org.example.server.http.Status;

public class UserController {

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
        } finally {
            return response;
        }

    }

}
