package com.boardcamp.api.builders;

import com.boardcamp.api.dtos.GameDTO;
import com.github.javafaker.Faker;

public class GameBuilder {
  private static Faker faker = new Faker();

  public static GameDTO create() {
    return new GameDTO(
        faker.name().fullName(),
        faker.avatar().image(),
        faker.number().numberBetween(1, Integer.MAX_VALUE),
        faker.number().numberBetween(1, Integer.MAX_VALUE));
  }

  public static GameDTO create(String name) {
    return new GameDTO(
        name,
        faker.avatar().image(),
        faker.number().numberBetween(1, Integer.MAX_VALUE),
        faker.number().numberBetween(1, Integer.MAX_VALUE));
  }
}
