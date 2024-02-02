package com.boardcamp.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({ BadRequestException.class })
  public ResponseEntity<Object> handlerBadRequest(BadRequestException exception) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
  }

  @ExceptionHandler({ CustomerNotFoundException.class })
  public ResponseEntity<Object> handlerCustomerNotFound(CustomerNotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }

  @ExceptionHandler({ GameNotFoundException.class })
  public ResponseEntity<Object> handlerGameNotFound(GameNotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }

  @ExceptionHandler({ RentalNotFoundException.class })
  public ResponseEntity<Object> handlerRentalNotFound(RentalNotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }

  @ExceptionHandler({ ConflictGameNameException.class })
  public ResponseEntity<Object> handlerConflictGameName(ConflictGameNameException exception) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
  }

  @ExceptionHandler({ ConflictCustomerCpfException.class })
  public ResponseEntity<Object> handlerConflictCustomerName(ConflictCustomerCpfException exception) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
  }

  @ExceptionHandler({ StockLimitGameRentalException.class })
  public ResponseEntity<Object> handlerStockLimitGameRental(StockLimitGameRentalException exception) {
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
  }

  @ExceptionHandler({ RentalFinishedException.class })
  public ResponseEntity<Object> handlerRentalFinished(RentalFinishedException exception) {
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
  }

}
