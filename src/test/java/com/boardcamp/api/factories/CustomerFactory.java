package com.boardcamp.api.factories;

import com.boardcamp.api.builders.CustomerBuilder;
import com.boardcamp.api.dtos.CustomerDTO;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.repositories.CustomerRepository;

public class CustomerFactory {

  public static CustomerModel create(CustomerRepository CustomerRepository) {
    CustomerDTO dto = CustomerBuilder.create();
    return CustomerRepository.save(new CustomerModel(dto));
  }

  public static CustomerModel create(CustomerRepository CustomerRepository, String cpf) {
    CustomerDTO dto = CustomerBuilder.create(cpf);
    return CustomerRepository.save(new CustomerModel(dto));
  }

}
