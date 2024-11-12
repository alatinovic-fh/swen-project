package org.example.server.util;

import java.io.*;
import java.net.Socket;

public class HttpSocket implements Closeable {

    private final Socket socket;
    private final BufferedReader reader;
    private final BufferedWriter writer;

    public HttpSocket(Socket socket) throws IOException {
        this.socket = socket;

        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public String readHttp() {
        StringBuilder requestBuilder = new StringBuilder();
        //Implement readHttp
        return null;
    }

    public void writeHttp(String http) throws IOException {
        this.writer.write(http);
        this.writer.flush();
    }

    @Override
    public void close() throws IOException {
        this.socket.close();
        this.reader.close();
        this.writer.close();
    }
}
