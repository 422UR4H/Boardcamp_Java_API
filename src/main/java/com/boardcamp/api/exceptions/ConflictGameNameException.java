package com.boardcamp.api.exceptions;

public class ConflictGameNameException extends RuntimeException {
  public ConflictGameNameException(String message) {
    super(message);
  }

  public ConflictGameNameException() {
    super("Game's name already exists");
  }
}
