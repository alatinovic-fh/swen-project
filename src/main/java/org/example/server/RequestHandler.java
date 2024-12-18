package org.example.server;

import org.example.server.http.Request;
import org.example.server.http.Response;
import org.example.server.util.HttpRequestParser;
import org.example.server.util.HttpResponseFormatter;
import org.example.server.util.HttpSocket;

import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;

/**
 * This class handles the incoming Requests
 *
 * @author Aleksandar Latinovic
 * */
public class RequestHandler implements Runnable{

    private final Socket socket;
    private final Application application;

    public RequestHandler(Socket socket, Application application){
        this.socket = socket;
        this.application = application;
    }


    @Override
    public void run() {
        this.handle();

    }


    public void handle(){
        HttpRequestParser httpRequestParser = new HttpRequestParser();
        HttpResponseFormatter httpResponseFormatter = new HttpResponseFormatter();

        // try with resources
        try (HttpSocket httpSocket = new HttpSocket(this.socket);) {
            String http = httpSocket.readHttp();
            Request request = httpRequestParser.parse(http);

            System.out.printf("%s %s %s \r\n", LocalDateTime.now(), request.getMethod(), request.getPath());

            Response response = this.application.handle(request);

            http = httpResponseFormatter.format(response);
            httpSocket.writeHttp(http);
        }catch (IOException e){
            // send standard error
            throw new RuntimeException(e);
        }

    }

}
