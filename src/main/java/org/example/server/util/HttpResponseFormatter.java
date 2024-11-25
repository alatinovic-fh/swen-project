package org.example.server.util;

import org.example.server.http.Response;

/**
 * This class contains the logic to format
 * Http-Responses
 *
 * @author Aleksandar Latinovic
 */
public class HttpResponseFormatter {

    /**
     * This method formats the Java-object "Response" to a Raw HTTP-Response
     * @param response the object to format
     * @return raw HTTP-Response
     */
    public String format(Response response) {
        StringBuilder result = new StringBuilder();

        //Set First Row
        if (response.getStatus() == null) throw new NoHttpStatusException("No status code");
        result.append("HTTP/1.1 ").
                append(response.getStatus().getCode()).
                append(" ").
                append(response.getStatus().getMessage()).
                append("\r\n");

        //Set Header
        if(response.getBody() == null || response.getBody().isEmpty()) { //Set Header Content-Length based on body length
            response.setHeader("Content-Length", "0");;
        }else{
            response.setHeader("Content-Length", String.valueOf(response.getBody().length()));
        }
        for(String key : response.getHeaders().keySet()){
            result.append(key).append(": ").
                    append(response.getHeaders().get(key)).
                    append("\r\n");
        }
        result.append("\r\n");

        //Set Body
        result.append(response.getBody());

        return result.toString();
    }
}
