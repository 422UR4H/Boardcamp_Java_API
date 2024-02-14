package com.boardcamp.api.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

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
    ConflictCustomerCpfException exception = assertThrows(
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
    verify(customerRepository, times(1)).save(new CustomerModel(dto));
  }

  @Test
  public void givenExistingCpf_whenUpdating_thenThrowsException() {
    // given
    CustomerModel customer = new CustomerModel(CustomerBuilder.create());
    CustomerDTO dto = CustomerBuilder.create();

    doReturn(Optional.of(customer)).when(customerRepository).findById(anyLong());
    doReturn(true).when(customerRepository).existsByCpf(any());

    // when
    ConflictCustomerCpfException exception = assertThrows(
        ConflictCustomerCpfException.class,
        () -> customerService.update(1L, dto));

    // then
    assertNotNull(exception);
    assertEquals("Customer's CPF already exists", exception.getMessage());
    verify(customerRepository, times(0)).save(new CustomerModel(dto));
  }

  @Test
  public void givenNonExistingCpf_whenUpdating_thenUpdatedCustomer() {
    // given
    final Long ID = 1L;
    CustomerModel customer = new CustomerModel(CustomerBuilder.create());
    CustomerDTO dto = CustomerBuilder.create();
    CustomerModel newCustomer = new CustomerModel(dto);

    newCustomer.setId(ID);
    doReturn(Optional.of(customer)).when(customerRepository).findById(anyLong());
    doReturn(false).when(customerRepository).existsByCpf(any());
    doReturn(newCustomer).when(customerRepository).save(newCustomer);

    // when
    CustomerModel result = customerService.update(ID, dto);

    // then
    assertNotNull(result);
    assertEquals(newCustomer, result);
    verify(customerRepository, times(1)).save(newCustomer);
  }

  @Test
  public void givenValidCustomer_whenUpdating_thenUpdatedCustomer() {
    // given
    final Long ID = 1L;
    CustomerModel customer = new CustomerModel(CustomerBuilder.create());
    CustomerDTO dto = CustomerBuilder.create(customer.getCpf());
    CustomerModel newCustomer = new CustomerModel(dto);

    newCustomer.setId(ID);
    doReturn(Optional.of(customer)).when(customerRepository).findById(anyLong());
    doReturn(newCustomer).when(customerRepository).save(newCustomer);

    // when
    CustomerModel result = customerService.update(ID, dto);

    // then
    assertNotNull(result);
    assertEquals(newCustomer, result);
    verify(customerRepository, times(1)).save(newCustomer);
    verify(customerRepository, times(0)).existsByCpf(any());
  }

}
