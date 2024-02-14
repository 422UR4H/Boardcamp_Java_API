package com.boardcamp.api.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.boardcamp.api.builders.RentalBuilder;
import com.boardcamp.api.dtos.RentalDTO;
import com.boardcamp.api.factories.CustomerFactory;
import com.boardcamp.api.factories.GameFactory;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.models.RentalModel;
import com.boardcamp.api.repositories.CustomerRepository;
import com.boardcamp.api.repositories.GameRepository;
import com.boardcamp.api.repositories.RentalRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class RentalIntegrationTests {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private RentalRepository rentalRepository;

  @Autowired
  private GameRepository gameRepository;

  @Autowired
  private CustomerRepository customerRepository;

  @BeforeEach
  public void cleanUpDatabase() {
    rentalRepository.deleteAll();
    gameRepository.deleteAll();
    customerRepository.deleteAll();
  }

  @Test
  public void givenNonExistingGame_whenCreating_thenThrowsError() {
    // given
    RentalDTO dto = RentalBuilder.create();

    @SuppressWarnings("null")
    HttpEntity<RentalDTO> body = new HttpEntity<>(dto);

    // when
    ResponseEntity<String> response = restTemplate.exchange(
        "/rentals", HttpMethod.POST, body, String.class);

    // then
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals(0, gameRepository.count());
    assertEquals(0, rentalRepository.count());
    assertEquals(0, customerRepository.count());
  }

  @Test
  public void givenNonExistingCustomer_whenCreating_thenThrowsError() {
    // given
    GameModel game = GameFactory.create(gameRepository);
    RentalDTO dto = RentalBuilder.create(1L, game.getId());

    @SuppressWarnings("null")
    HttpEntity<RentalDTO> body = new HttpEntity<>(dto);

    // when
    ResponseEntity<String> response = restTemplate.exchange(
        "/rentals", HttpMethod.POST, body, String.class);

    // then
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals(1, gameRepository.count());
    assertEquals(0, rentalRepository.count());
    assertEquals(0, customerRepository.count());
  }

  @Test
  @SuppressWarnings("null")
  public void givenValidRental_whenCreating_thenCreatesRental() {
    // given
    CustomerModel customer = CustomerFactory.create(customerRepository);
    GameModel game = GameFactory.create(gameRepository);
    RentalDTO dto = RentalBuilder.create(customer.getId(), game.getId());
    HttpEntity<RentalDTO> body = new HttpEntity<>(dto);

    // when
    ResponseEntity<RentalModel> response = restTemplate.exchange(
        "/rentals", HttpMethod.POST, body, RentalModel.class);

    // then
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(customer.getId(), response.getBody().getCustomer().getId());
    assertEquals(game.getId(), response.getBody().getGame().getId());
    assertEquals(1, rentalRepository.count());
  }

}
