package org.example.application.service;

import org.example.application.data.CardRepository;
import org.example.application.entity.Card;
import org.example.application.exception.AuthenticationFailedException;
import org.example.application.exception.DeckConflictException;
import org.example.application.exception.InternalServerError;
import org.example.application.exception.UnauthorizedException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CardService {

    private CardRepository cardRepository;

    public CardService() {
        this.cardRepository = new CardRepository();
    }

    public List<Card> getUserCards(String token) {
        List<Card> cards = null;
        String username = extractUsername(token);
        return this.cardRepository.findCardsByUsername(username);
    }

    public List<Card> getUserDeck(String token) {
        List<Card> cards = null;
        String username = extractUsername(token);
        cards = this.cardRepository.findDeckByUsername(username);
        return cards;

    }

    public void setDeck(String token, List<String> cardIds) {
        String username = extractUsername(token);
        if(cardIds == null || cardIds.size() != 4) throw new DeckConflictException("A deck can only contain 4 cards");
        if(checkDeck(token, cardIds)) throw new UnauthorizedException("Atleast one card does not exist or belongs to another user");
        if(this.cardRepository.updateDeck(cardIds, username)){
            System.out.println("True");
        }else{
            System.out.println("False");
        }
    }

    private boolean checkDeck(String token, List<String> cardIds) {
        List<Card> ownerCards = getUserCards(token);
        List<String> ownerCardIds = new ArrayList<>();
        for(Card card : ownerCards){
            ownerCardIds.add(card.getId());
        }
        for (String cardId : cardIds) {
            if (!ownerCardIds.contains(cardId)) return true;
        }
        return false;
    }

    private String extractUsername(String token) {
        if (token.startsWith("Bearer ") && token.contains("-mtcgToken")) {
            Pattern pattern = Pattern.compile("Bearer\\s(\\w+)-");
            Matcher matcher = pattern.matcher(token);
            // Check if a match is found
            if (matcher.find()) {
                return matcher.group(1); // Extract the username
            }
        } else {
            throw new AuthenticationFailedException("Authentication failed");

        }
        throw new InternalServerError("Internal Server Error");
    }



}
