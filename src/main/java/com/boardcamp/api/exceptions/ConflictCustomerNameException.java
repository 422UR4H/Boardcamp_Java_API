package com.boardcamp.api.exceptions;

public class ConflictCustomerNameException extends RuntimeException {
  public ConflictCustomerNameException(String message) {
    super(message);
  }

  public ConflictCustomerNameException() {
    super("Customer's name already exists");
  }
}
