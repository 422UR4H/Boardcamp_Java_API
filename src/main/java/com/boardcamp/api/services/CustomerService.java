package com.boardcamp.api.services;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.CreateCustomerDTO;
import com.boardcamp.api.exceptions.ConflictCustomerNameException;
import com.boardcamp.api.exceptions.CustomerNotFoundException;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.repositories.CustomerRepository;

import lombok.NonNull;

@Service
public class CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public CustomerModel findById(@NonNull Long id) {
    return customerRepository.findById(id).orElseThrow(() -> {
      throw new CustomerNotFoundException();
    });
  }

  public CustomerModel create(CreateCustomerDTO dto) {
    if (customerRepository.existsByName(dto.getName())) {
      throw new ConflictCustomerNameException();
    }
    return customerRepository.save(new CustomerModel(dto));
  }

}
