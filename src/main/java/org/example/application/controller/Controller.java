package org.example.application.controller;

import org.example.server.http.Request;
import org.example.server.http.Response;


/**
 *
 */
public abstract class Controller {

    public abstract Response handle(Request request);
}