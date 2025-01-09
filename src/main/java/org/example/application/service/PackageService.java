package org.example.application.service;

import org.example.application.data.PackageRepository;
import org.example.application.entity.Card;
import org.example.application.exception.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PackageService {

    private final PackageRepository packageRepository;

    public PackageService() {
        this.packageRepository = new PackageRepository();
    }

    public void addPackage(String token, List<Card> cards){
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
    }


    public List<Card> buyPackage(String token){
        List<Card> cards = null;
        String username = "";
        int packageId = this.verifyPackageAvailability();
        if(packageId == 0) throw new PackageConflictException("No Packages available");
        if(token.startsWith("Bearer ") && token.contains("-mtcgToken")){
            Pattern pattern = Pattern.compile("Bearer\\s(\\w+)-");
            Matcher matcher = pattern.matcher(token);
            // Check if a match is found
            if (matcher.find()) {
                username = matcher.group(1); // Extract the username
            }
        }else{
            throw new AuthenticationFailedException("Authentication failed");
        }
        if(!this.verifyCoins(username)) throw new NotEnoughCoinsException("Not enough coins");
        if(this.packageRepository.updatePackageToBought(packageId) && this.packageRepository.assignCardsToUser(username,packageId) && this.packageRepository.updateCoins(username)){
            cards = this.packageRepository.getPackageCards(packageId);
            if (cards == null){
                throw new InternalServerError("Cards not found");
            }else
                return cards;
        }
        throw new InternalServerError("Interal Server error");
    }


    private boolean verifyCoins(String username){
        if(this.packageRepository.findCoinsByUsername(username) >= 5) return true;
        return false;
    }

    private int verifyPackageAvailability(){
        return this.packageRepository.findAvailablePackage();
    }

}
