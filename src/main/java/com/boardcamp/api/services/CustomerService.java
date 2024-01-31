package com.boardcamp.api.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.repositories.CustomerRepository;

import lombok.NonNull;

@Service
public class CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public Optional<CustomerModel> findCustomerById(@NonNull Long id) {
    Optional<CustomerModel> customer = customerRepository.findById(id);

    if (!customer.isPresent()) {
      return Optional.empty();
    }
    return Optional.of(customer.get());
  }

}
