package com.boardcamp.api.builders;

import com.boardcamp.api.dtos.RentalDTO;
import com.github.javafaker.Faker;

public class RentalBuilder {
  private static final Faker faker = new Faker();

  public static RentalDTO create() {
    return new RentalDTO(
        faker.number().randomNumber(),
        faker.number().randomNumber(),
        faker.number().numberBetween(1, Integer.MAX_VALUE));
  }

  public static RentalDTO create(Long customerId, Long gameId) {
    return new RentalDTO(customerId, gameId,
        faker.number().numberBetween(1, Integer.MAX_VALUE));
  }
}
