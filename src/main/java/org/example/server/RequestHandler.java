package org.example.server;

import org.example.server.http.Request;
import org.example.server.http.Response;
import org.example.server.util.HttpRequestParser;
import org.example.server.util.HttpResponseFormatter;
import org.example.server.util.HttpSocket;

import java.io.IOException;
import java.net.Socket;

public class RequestHandler {

    // [x]receive Socket
    // [x]wrap Socket in HttpSocket
    // [x] get HTTP request
    // give response to Application#
    // receive response
    // format response to HTTP response
    // send response to client

    private final Socket socket;
    private final Application application;

    public RequestHandler(Socket socket, Application application){
        this.socket = socket;
        this.application = application;
    }


    public void handle(){
        HttpRequestParser httpRequestParser = new HttpRequestParser();
        HttpResponseFormatter httpResponseFormatter = new HttpResponseFormatter();

        // try with resources
        try (HttpSocket httpSocket = new HttpSocket(this.socket);) {
            String http = httpSocket.readHttp();
            Request request = httpRequestParser.parse(http);

            Response response = this.application.handle(request);

            http = httpResponseFormatter.format(response);
            httpSocket.writeHttp(http);
        }catch (IOException e){
            // send standard error
            throw new RuntimeException(e);
        }

    }
}
