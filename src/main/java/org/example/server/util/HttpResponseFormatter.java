package org.example.server.util;

import org.example.server.http.Response;

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
        //result.append(response.getHeadersAsString());
        for(String key : response.getHeaders().keySet()){
            result.append(key).append(": ").append(response.getHeaders().get(key)).append("\r\n");
        }
        if(response.getBody() == null) { //Set Header Content-Length based on body length
            result.append("Content-Length: ").append("0").append("\r\n");
        }else{
            result.append("Content-Length: ").append(response.getBody().length()).append("\r\n");
        }
        result.append("\r\n");

        //Set Body
        result.append(response.getBody());

        return result.toString();
    }
}
