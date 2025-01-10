package org.example.application.controller;

import org.example.application.entity.Card;
import org.example.application.exception.AuthenticationFailedException;
import org.example.application.exception.DeckConflictException;
import org.example.application.exception.UnauthorizedException;
import org.example.application.service.CardService;
import org.example.server.http.Request;
import org.example.server.http.Response;
import org.example.server.http.Status;

import java.util.List;

public class CardController extends Controller {

    private CardService cardService;

    public CardController() {
        this.cardService = new CardService();
    }

    public Response showCards(Request request){
        Response response = new Response();
        try{
            String token = request.getHeader("Authorization");
            List<Card> userCards = this.cardService.getUserCards(token);
            if (userCards == null || userCards.isEmpty()){
                response.setStatus(Status.NO_CONTENT);
                response.setBody("User has no cards");
            }else{
                response = json(Status.OK, userCards);
            }
        }catch(AuthenticationFailedException e){
            response.setStatus(Status.UNAUTHORIZED);
            response.setBody(e.getMessage());
        }
        return response;
    }

    public Response showDeck(Request request){
        Response response = new Response();
        try{
            String token = request.getHeader("Authorization");
            List<Card> userDeck = this.cardService.getUserDeck(token);
            if (userDeck == null || userDeck.isEmpty()){
                response.setStatus(Status.NO_CONTENT);
                response.setBody("User has no cards");
            }else{
                response = json(Status.OK, userDeck);
            }
        }catch(AuthenticationFailedException e){
            response.setStatus(Status.UNAUTHORIZED);
            response.setBody(e.getMessage());
        }
        return response;
    }

    public Response configureDeck(Request request){
        Response response = new Response();
        try{
            String token = request.getHeader("Authorization");
            this.cardService.setDeck(token, fromBodyAsList(request.getBody(), String.class));
            response = json(Status.OK, "");
        }catch(AuthenticationFailedException e){
            response.setStatus(Status.UNAUTHORIZED);
            response.setBody(e.getMessage());
        }catch (DeckConflictException e){
            response.setStatus(Status.BAD_REQUEST);
            response.setBody(e.getMessage());
        }catch (UnauthorizedException e){
            response.setStatus(Status.FORBIDDEN);
            response.setBody(e.getMessage());
        }
        return response;
    }





    @Override
    public Response handle(Request request) {
        if (request.getMethod().getName().equals("GET") && request.getPath().startsWith("/cards")) {
            return this.showCards(request);
        }else if (request.getMethod().getName().equals("GET") && request.getPath().startsWith("/deck")) {
            return this.showDeck(request);
        }else if (request.getMethod().getName().equals("PUT") && request.getPath().startsWith("/deck")) {
            return this.configureDeck(request);
        }
        return null;
    }
}
