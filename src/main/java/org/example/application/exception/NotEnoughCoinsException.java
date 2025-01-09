package org.example.application.exception;

public class NotEnoughCoinsException extends RuntimeException {
    public NotEnoughCoinsException(String message) {
        super(message);
    }
}
