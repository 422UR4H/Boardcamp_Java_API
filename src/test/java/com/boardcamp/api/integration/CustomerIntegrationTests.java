package com.boardcamp.api.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.boardcamp.api.builders.CustomerBuilder;
import com.boardcamp.api.dtos.CustomerDTO;
import com.boardcamp.api.factories.CustomerFactory;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.repositories.CustomerRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CustomerIntegrationTests {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private CustomerRepository customerRepository;

  @BeforeEach
  public void cleanUpDatabase() {
    customerRepository.deleteAll();
  }

  @Test
  @SuppressWarnings("null")
  public void givenRepeatedCustomerCpf_whenCreating_thenThrowsException() {
    // given
    CustomerModel customer = CustomerFactory.create(customerRepository);
    CustomerDTO repeatedCustomerDTO = CustomerBuilder.create(customer.getCpf());

    HttpEntity<CustomerDTO> body = new HttpEntity<>(repeatedCustomerDTO);

    // when
    ResponseEntity<String> response = restTemplate.exchange(
        "/customers", HttpMethod.POST, body, String.class);

    assertEquals("Customer's CPF already exists", response.getBody());
    assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    assertEquals(1, customerRepository.count());
  }

  @Test
  public void givenValidCustomer_whenCreating_thenCreatesCustomer() {
    // given
    CustomerDTO dto = CustomerBuilder.create();

    @SuppressWarnings("null")
    HttpEntity<CustomerDTO> body = new HttpEntity<>(dto);

    // when
    ResponseEntity<CustomerModel> response = restTemplate.exchange(
        "/customers", HttpMethod.POST, body, CustomerModel.class);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(1, customerRepository.count());
  }
}
