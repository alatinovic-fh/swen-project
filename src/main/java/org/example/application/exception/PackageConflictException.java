package org.example.application.exception;

public class PackageConflictException extends RuntimeException {
    public PackageConflictException(String message) {
        super(message);
    }
}
