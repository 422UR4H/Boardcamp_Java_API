package com.boardcamp.api.dtos;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalDTO {

  @Positive(message = "Field customerId must be a valid id greater than 0")
  private Long customerId;

  @Positive(message = "Field gameId must be a valid id greater than 0")
  private Long gameId;

  @Positive(message = "Field daysRented must be greater than 0")
  private int daysRented;

}
