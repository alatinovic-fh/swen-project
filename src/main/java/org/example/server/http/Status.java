package org.example.server.http;

public enum Status {
    OK(200, "OK"),
    CREATED(201, "CREATED"),
    NOT_FOUND(404, "OK"),
    CONFLICT(409, "CONFLICT"),
    UNAUTHORIZED(401, "UNAUTHORIZED"),
    FORBIDDEN(403, "FORBIDDEN"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    NO_CONTENT(204,  "No Content"),
    BAD_REQUEST(400, "Bad Request");

    private final int code;
    private final String message;

    Status(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
