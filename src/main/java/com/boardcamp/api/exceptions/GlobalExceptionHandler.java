package com.boardcamp.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({ BadRequestException.class })
  public ResponseEntity<String> handlerBadRequest(BadRequestException exception) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
  }

  @ExceptionHandler({ CustomerNotFoundException.class })
  public ResponseEntity<String> handlerCustomerNotFound(CustomerNotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }

  @ExceptionHandler({ GameNotFoundException.class })
  public ResponseEntity<String> handlerGameNotFound(GameNotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }

  @ExceptionHandler({ RentalNotFoundException.class })
  public ResponseEntity<String> handlerRentalNotFound(RentalNotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }

  @ExceptionHandler({ ConflictGameNameException.class })
  public ResponseEntity<String> handlerConflictGameName(ConflictGameNameException exception) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
  }

  @ExceptionHandler({ ConflictCustomerCpfException.class })
  public ResponseEntity<String> handlerConflictCustomerName(ConflictCustomerCpfException exception) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
  }

  @ExceptionHandler({ StockLimitGameRentalException.class })
  public ResponseEntity<String> handlerStockLimitGameRental(StockLimitGameRentalException exception) {
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
  }

  @ExceptionHandler({ RentalFinishedException.class })
  public ResponseEntity<String> handlerRentalFinished(RentalFinishedException exception) {
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
  }

}
