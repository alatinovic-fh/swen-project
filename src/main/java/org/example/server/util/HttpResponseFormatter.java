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
        result.append(response.headersToString());
        result.append("\r\n");

        //Set Body
        result.append(response.getBody());

        return result.toString();
    }
}
