package org.example.application.controller;

import org.example.application.dto.UserCredentials;
import org.example.application.dto.UserData;
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

    private final UserService userService;
    // TODO Constructor

    public UserController() {
        super();
        this.userService = new UserService();
    }

    /**
     * This method registers a new User to the database
     *
     * @param request
     * @return the registered user
     */
    private Response register(Request request) {
        UserCredentials userCredentials = fromBody(request.getBody(), UserCredentials.class);
        Response response = new Response();
        try {
            userCredentials = userService.create(userCredentials);
            response = json(Status.CREATED, userCredentials);
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

    public Response retrieveData(Request request) {
        Response response = new Response();
        try{
            String searchedUser = request.getPath().split("/")[2];
            String token = request.getHeader("Authorization");
            UserData userData = userService.getUserData(token, searchedUser);
            response = json(Status.OK, userData);
        }catch (AuthenticationFailedException e){
            response.setStatus(Status.UNAUTHORIZED);
            response.setBody(e.getMessage());
        }catch (UserNotFoundException e) {
            response.setStatus(Status.NOT_FOUND);
            response.setBody(e.getMessage());
        }
        return response;
    }

    public Response updateUserData(Request request) {
        Response response = new Response();
        try {
            String searchedUser = request.getPath().split("/")[2];
            String token = request.getHeader("Authorization");
            UserData userData = fromBody(request.getBody(), UserData.class);
            UserData updatedUserData = userService.setUserData(token, searchedUser, userData);
            response = json(Status.OK, updatedUserData);
        }catch (AuthenticationFailedException e){
            response.setStatus(Status.UNAUTHORIZED);
            response.setBody(e.getMessage());
        }catch (UserNotFoundException e) {
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
        if (request.getMethod().getName().equals("POST") && request.getPath().startsWith("/user")) {
            return register(request);
        } else if (request.getMethod().getName().equals("POST") && request.getPath().startsWith("/sessions")) {
            return login(request);
        } else if(request.getMethod().getName().equals("GET") && request.getPath().startsWith("/users/")) {
            return retrieveData(request);
        } else if(request.getMethod().getName().equals("PUT") && request.getPath().startsWith("/users/")) {
            return updateUserData(request);
        }
        return null;
    }
}
