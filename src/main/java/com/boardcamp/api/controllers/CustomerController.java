package com.boardcamp.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.services.CustomerService;

import lombok.NonNull;

@RestController
@RequestMapping("customers")
public class CustomerController {

  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getCustomerById(@PathVariable("id") @NonNull Long id) {
    CustomerModel customer = customerService.findCustomerById(id);
    return ResponseEntity.status(HttpStatus.OK).body(customer);
  }
}
