package org.example.application.controller;

import org.example.application.entity.Card;
import org.example.application.exception.*;
import org.example.application.service.PackageService;
import org.example.server.http.Request;
import org.example.server.http.Response;
import org.example.server.http.Status;

import java.util.ArrayList;
import java.util.List;

public class PackageController extends Controller {

    private final PackageService packageService;

    public PackageController() {
        super();
        this.packageService = new PackageService();
    }

    public Response create(Request request) {
        Response response = new Response();
        try {
            String token = request.getHeader("Authorization");
            List<Card> cards = new ArrayList(fromBodyAsList(request.getBody(), Card.class));
            this.packageService.addPackage(token, cards);
            response = json(Status.CREATED, "");
        }catch (AuthenticationFailedException e){
            response.setStatus(Status.UNAUTHORIZED);
            response.setBody(e.getMessage());
        }catch (UserNotFoundException e) {
            response.setStatus(Status.NOT_FOUND);
            response.setBody(e.getMessage());
        }catch (UnauthorizedException e){
            response.setStatus(Status.FORBIDDEN);
            response.setBody(e.getMessage());
        }catch (PackageConflictException e){
            response.setStatus(Status.CONFLICT);
            response.setBody(e.getMessage());
        }

        return response;
    }

    public Response aquire(Request request) {
        Response response = new Response();
        try{
            String token = request.getHeader("Authorization");
            List<Card> aquiredCards = this.packageService.buyPackage(token);
            response = json(Status.OK, aquiredCards);

        }catch (AuthenticationFailedException e){
            response.setStatus(Status.UNAUTHORIZED);
            response.setBody(e.getMessage());
        }catch (NotEnoughCoinsException e){
            response.setStatus(Status.FORBIDDEN);
            response.setBody(e.getMessage());
        }catch (PackageConflictException e){
            response.setStatus(Status.NOT_FOUND);
            response.setBody(e.getMessage());
        }

        return response;
    }



    @Override
    public Response handle(Request request) {
        if (request.getMethod().getName().equals("POST") && request.getPath().startsWith("/packages")) {
            return this.create(request);
        }else if (request.getMethod().getName().equals("POST") && request.getPath().startsWith("/transactions")) {
            return this.aquire(request);
        }
        return null;
    }
}
