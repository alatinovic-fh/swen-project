package org.example.application.exception;

public class ControllerNotFound extends RuntimeException {
    public ControllerNotFound(String message) {
        super(message);
    }
}
