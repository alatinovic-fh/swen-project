package org.example.application.routing;

public class ControllerNotFound extends RuntimeException {
    public ControllerNotFound(String message) {
        super(message);
    }
}
