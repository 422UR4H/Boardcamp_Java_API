package com.boardcamp.api.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.boardcamp.api.builders.CustomerBuilder;
import com.boardcamp.api.dtos.CustomerDTO;
import com.boardcamp.api.exceptions.ConflictCustomerCpfException;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.repositories.CustomerRepository;
import com.boardcamp.api.services.CustomerService;

@SpringBootTest
public class CustomerUnitTests {

  @InjectMocks
  private CustomerService customerService;

  @Mock
  private CustomerRepository customerRepository;

  @Test
  public void givenRepeatedCustomerCpf_whenCreating_thenThrowsException() {
    // given
    CustomerDTO dto = CustomerBuilder.create();
    doReturn(true).when(customerRepository).existsByCpf(any());

    // when
    ConflictCustomerCpfException exception = assertThrowsExactly(
        ConflictCustomerCpfException.class,
        () -> customerService.create(dto));

    // then
    assertNotNull(exception);
    assertEquals("Customer's CPF already exists", exception.getMessage());
    verify(customerRepository, times(0)).save(new CustomerModel(dto));
  }

  @Test
  public void givenValidCustomer_whenCreating_thenCreatesCustomer() {
    // given
    CustomerDTO dto = CustomerBuilder.create();
    CustomerModel customer = new CustomerModel(dto);

    doReturn(false).when(customerRepository).existsByCpf(any());
    doReturn(customer).when(customerRepository).save(customer);

    // when
    CustomerModel result = customerService.create(dto);

    // then
    assertNotNull(result);
    assertEquals(customer, result);
    verify(customerRepository, times(1)).existsByCpf(any());
    verify(customerRepository, times(1)).save(new CustomerModel(dto));
  }

}
