package org.example.application.controller;

import org.example.application.entity.User;
import org.example.application.exception.AuthenticationFailedException;
import org.example.application.exception.UserAlreadyExistsException;
import org.example.application.exception.UserNotFoundException;
import org.example.application.service.UserService;
import org.example.server.http.Request;
import org.example.server.http.Response;
import org.example.server.http.Status;
/**
 * This class handles the Requests for the User
 *
 * @author Aleksandar Latinovic
 * */

public class UserController extends Controller {

    private final UserService userService = new UserService();
    // TODO Constructor

    /**
     * This method registers a new User to the database
     *
     * @param request
     * @return the registered user
     */
    private Response register(Request request) {
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

    /**
     * This method is used to login a user and returns a Token to
     * authenticate the user
     *
     * @param request
     * @return the response with the token
     */
    public Response login(Request request) {
        Response response = new Response();
        User user = fromBody(request.getBody(), User.class);
        try{
            String token = userService.auth(user);
            response.setStatus(Status.OK);
            response.setBody(token);
        }catch (AuthenticationFailedException e) {
            response.setStatus(Status.UNAUTHORIZED);
            response.setBody(e.getMessage());
        } catch (UserNotFoundException e) {
            response.setStatus(Status.NOT_FOUND);
            response.setBody(e.getMessage());
        }
        return response;
    }

    /**
     * This method defines the used Route
     *
     * @param request
     * @return the Response specific to the Route
     */
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
