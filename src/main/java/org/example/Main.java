package org.example;

import org.example.application.MonsterTradingCard;
import org.example.server.Server;

public class Main {
    public static void main(String[] args) {
        //Start Server
        Server server = new Server(new MonsterTradingCard());
        server.start();
        //Stop Server
    }
}