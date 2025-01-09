package org.example.application.service;

import org.example.application.data.PackageRepository;
import org.example.application.entity.Card;
import org.example.application.exception.AuthenticationFailedException;
import org.example.application.exception.PackageConflictException;
import org.example.application.exception.UnauthorizedException;

import java.util.ArrayList;
import java.util.List;

public class PackageService {

    private final PackageRepository packageRepository;

    public PackageService() {
        this.packageRepository = new PackageRepository();
    }

    public boolean addPackage(String token, List<Card> cards){
        ArrayList<String> cardIds = new ArrayList<>();
        for(Card card : cards){
            cardIds.add(card.getId());
        }
        if (!token.equals("Bearer admin-mtcgToken")) {
            throw new AuthenticationFailedException("Authentication failed");
        } else if(!token.contains("admin-mtcgToken")) {
            throw new UnauthorizedException("Unauthorized");
        } else if(this.packageRepository.cardExists(cardIds)){
            throw new PackageConflictException("Card in package already exists");
        }
        this.packageRepository.insertPackage(cards, cardIds);
        return true;
    }



    private boolean verifyCoins(String username){
        if(this.packageRepository.findCoinsByUsername(username) >= 5) return true;
        return false;
    }

}
