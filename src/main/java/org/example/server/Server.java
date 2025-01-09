package org.example.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private final Application application;
    private ServerSocket serverSocket;

    public Server(Application application) {
        this.application = application;
    }

    public void start() {
        //open port
        try {
            this.serverSocket = new ServerSocket(10001);
            System.out.println("Server started");
            System.out.println("Listening on port 10001");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try(ExecutorService threadpool = Executors.newFixedThreadPool(5);){
            //Wait for request
            while (true) {
                try {
                    Socket socket = this.serverSocket.accept();
                    RequestHandler requestHandler = new RequestHandler(socket, this.application);
                    threadpool.submit(requestHandler);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
