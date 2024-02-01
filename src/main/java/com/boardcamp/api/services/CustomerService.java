package com.boardcamp.api.services;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.CustomerDTO;
import com.boardcamp.api.exceptions.ConflictCustomerCpfException;
import com.boardcamp.api.exceptions.CustomerNotFoundException;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.repositories.CustomerRepository;

import jakarta.validation.Valid;
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

  public CustomerModel create(CustomerDTO dto) {
    if (customerRepository.existsByCpf(dto.getCpf())) {
      throw new ConflictCustomerCpfException();
    }
    return customerRepository.save(new CustomerModel(dto));
  }

  public CustomerModel update(@NonNull Long id, @Valid CustomerDTO dto) {
    if (!customerRepository.existsById(id)) {
      throw new CustomerNotFoundException();
    }
    if (!customerRepository.existsByCpf(dto.getCpf())) {
      throw new ConflictCustomerCpfException();
    }
    CustomerModel customer = new CustomerModel(dto);

    customer.setId(id);
    return customerRepository.save(customer);
  }

}
