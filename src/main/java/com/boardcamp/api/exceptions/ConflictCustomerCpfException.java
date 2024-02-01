package com.boardcamp.api.exceptions;

public class ConflictCustomerCpfException extends RuntimeException {
  public ConflictCustomerCpfException(String message) {
    super(message);
  }

  public ConflictCustomerCpfException() {
    super("Customer's CPF already exists");
  }
}
