package org.example;

import org.example.server.http.Request;
import org.example.server.util.HttpRequestParser;

public class Main {
    public static void main(String[] args) {
        //Start Server
        //Stop Server
        HttpRequestParser parser = new HttpRequestParser();
        Request req = new HttpRequestParser().parse("POST /api/resource HTTP/1.1\r\n" +
                "Host: example.com\r\n" +
                "Content-Type: application/json\r\n" +
                "Authorization: Bearer your_token_here\r\n" +
                "Content-Length: 75\r\n" +
                "\r\n" +
                "{\r\n" +
                "  \"username\": \"user123\",\r\n" +
                "  \"password\": \"pass123\",\r\n" +
                "  \"email\": \"user@example.com\"\r\n" +
                "}");

        System.out.println("Method: " + req.getMethod());
        System.out.println("User-Agent: " + req.getHeader("User-Agent"));
        System.out.println("Hosts: " + req.getHeader("Host"));
        System.out.println("Accept: " + req.getHeader("Accept"));
        System.out.println("Body: " + req.getBody());
    }
}