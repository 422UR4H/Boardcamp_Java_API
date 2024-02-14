package com.boardcamp.api.factories;

import com.boardcamp.api.builders.CustomerBuilder;
import com.boardcamp.api.dtos.CustomerDTO;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.repositories.CustomerRepository;

public class CustomerFactory {

  public static CustomerModel create(CustomerRepository customerRepository) {
    CustomerDTO dto = CustomerBuilder.create();
    return customerRepository.save(new CustomerModel(dto));
  }

  public static CustomerModel create(CustomerRepository customerRepository, String cpf) {
    CustomerDTO dto = CustomerBuilder.create(cpf);
    return customerRepository.save(new CustomerModel(dto));
  }

}
