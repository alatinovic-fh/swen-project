package org.example.server.util;

import org.example.server.http.Method;
import org.example.server.http.Request;

public class HttpRequestParser {

    /**
     * This method parses a raw HTTP-Request to a Request-object
     * @param http raw HTTP-Request as String
     * @return a Request object
     */
    public Request parse(String http) {
        Request request = new Request();
        String[] lines = http.split("\r\n");
        String[] firstLine = lines[0].split(" ");

        //Set Method
        request.setMethod(Method.valueOf(firstLine[0]));
        //Set Path
        request.setPath(firstLine[1]);

        //Set Headers


        int i = 1; //Start after the first line
        /* My Version
        while(!lines[i].isEmpty() && (i+1) < lines.length) { //in case of GET/DELETE there is no empty line so i is not allowed to be bigger than lines.length
            String[] headerParts = lines[i].split(":", 2); //Split Header entries
            request.setHeader(headerParts[0], headerParts[1].trim()); //add entries to Map
            i++; //Next Line
        }
        */

        //For Version
        for(int j = i; j<lines.length; j++) {
            if(lines[j].isEmpty()) break;
            String[] headerParts = lines[j].split(":",2);
            request.setHeader(headerParts[0], headerParts[1].trim());
        }

        //Set Body if present
        StringBuilder bodyBuilder = new StringBuilder();
        for (int j = i + 1; j < lines.length; j++) {
            bodyBuilder.append(lines[j]).append("\n");
        }
        if (!bodyBuilder.isEmpty()) {
            request.setBody(bodyBuilder.toString().trim()); //creates Body-String
        }
        return request;

    }
}
