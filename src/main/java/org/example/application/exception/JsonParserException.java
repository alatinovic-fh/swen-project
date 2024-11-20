package org.example.application.exception;

public class JsonParserException extends RuntimeException {
  public JsonParserException(String message) {
    super(message);
  }

  public JsonParserException(Throwable cause) {
    super(cause);
  }
}