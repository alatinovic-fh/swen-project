package org.example.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.application.entity.User;
import org.example.application.service.UserService;
import org.example.server.http.Request;
import org.example.server.http.Response;
import org.example.server.http.Status;

import java.io.ObjectStreamException;

public class UserController {

    private final UserService userService = new UserService();

    public Response register(Request request) {
        //request -> User
        ObjectMapper objectMapper = new ObjectMapper();
        User user = null;
        try {
            user = objectMapper.readValue(request.getBody(), User.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        user = userService.create(user);
        Response response = new Response();
        response.setStatus(Status.CREATED);
        response.setHeader("Content-Type", "application/json");
        response.setBody("{ \"Username\": \"" + user.getUsername() + "\" }");
        return response;
    }

}
