package org.example.application.controller;

import org.example.application.service.CardService;
import org.example.server.http.Request;
import org.example.server.http.Response;

public class CardController extends Controller {

    private CardService cardService;

    public CardController() {
        this.cardService = new CardService();
    }

    public Response showCards(Request request){

    }

    public Response showDeck(Request request){

    }

    public Response configureDeck(Request request){

    }





    @Override
    public Response handle(Request request) {
        return null;
    }
}
