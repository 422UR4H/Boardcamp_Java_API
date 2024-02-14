package com.boardcamp.api.builders;

import com.boardcamp.api.dtos.CustomerDTO;
import com.github.javafaker.Faker;

public class CustomerBuilder {
  private static final Faker faker = new Faker();

  private static String randomCpf() {
    String cpf = "";

    for (int i = 0; i < 11; i++) {
      cpf += faker.number().randomDigit();
    }
    return cpf;
  }

  public static CustomerDTO create() {
    return new CustomerDTO(faker.name().fullName(), randomCpf());
  }

  public static CustomerDTO create(String cpf) {
    return new CustomerDTO(faker.name().fullName(), cpf);
  }
}
