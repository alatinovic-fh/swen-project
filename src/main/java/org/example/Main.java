package org.example;

import org.example.application.html.SimpleHtmlApplication;
import org.example.server.Server;
import org.example.server.http.Request;
import org.example.server.http.Response;
import org.example.server.http.Status;
import org.example.server.util.HttpRequestParser;
import org.example.server.util.HttpResponseFormatter;

public class Main {
    public static void main(String[] args) {
        //Start Server
        Server server = new Server(new SimpleHtmlApplication());
        server.start();
        //Stop Server
    }
}