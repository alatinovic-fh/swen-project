package org.example.server.util;

import org.example.server.http.Method;
import org.example.server.http.Request;

/**
 * This class contains the logic to parse a Http-Request
 *
 * @author Aleksandar Latinovic
 */
public class HttpRequestParser {

    /**
     * This method parses a raw HTTP-Request to a Request-object
     * @param http raw HTTP-Request as String
     * @return a Request object
     */
    public Request parse(String http) {
        Request request = new Request();
        request.setHttp(http);

        String[] lines = http.split("\r\n");
        String requestLine = lines[0];
        String[] requestLineParts = requestLine.split(" ");

        request.setMethod(Method.valueOf(requestLineParts[0]));
        request.setPath(requestLineParts[1]);

        // parse headers
        int emptyLine = 0;
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];

            if (line.isEmpty()) {
                emptyLine = i;
                break;
            }

            String[] headerParts = line.split(":", 2);
            request.setHeader(headerParts[0], headerParts[1].trim());
        }

        if (emptyLine == 0 || lines.length - 1 == emptyLine) {
            return request;
        }

        // parse body
        StringBuilder bodyBuilder = new StringBuilder();
        for (int i = emptyLine + 1; i < lines.length; i++) {
            bodyBuilder.append(lines[i]);

            if (lines.length - 1 != i) {
                bodyBuilder.append("\r\n");
            }
        }

        request.setBody(bodyBuilder.toString());

        return request;
    }
}
