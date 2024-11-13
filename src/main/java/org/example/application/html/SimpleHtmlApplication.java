package org.example.application.html;

import org.example.server.Application;
import org.example.server.http.Request;
import org.example.server.http.Response;
import org.example.server.http.Status;

public class SimpleHtmlApplication implements Application {

    @Override
    public Response handle(Request request) {
        StringBuilder result = new StringBuilder();
        Response response = new Response();
        response.setStatus(Status.OK);
        response.setHeader("Content-Type", "text/html");
        response.setHeader("Cache-Control", "no-cache");
        result.append("<html>");
        result.append("<head>");
        result.append("<title>Simple HTML Application</title>");
        result.append("</head>");
        result.append("<body>");
        result.append("<h1>Simple HTML Application</h1>");
        result.append("</body>");
        result.append("</html>");
        response.setHeader("Content-Length", String.valueOf(result.toString().length()));
        response.setBody(result.toString());
        return response;
    }
}
