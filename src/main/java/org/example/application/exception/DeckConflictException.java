package org.example.application.exception;

public class DeckConflictException extends RuntimeException {
    public DeckConflictException(String message) {
        super(message);
    }
}
