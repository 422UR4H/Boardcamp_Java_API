package com.boardcamp.api.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.boardcamp.api.builders.GameBuilder;
import com.boardcamp.api.dtos.GameDTO;
import com.boardcamp.api.factories.GameFactory;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.repositories.GameRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class GameIntegrationTests {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private GameRepository gameRepository;

  @BeforeEach
  public void cleanUpDatabase() {
    gameRepository.deleteAll();
  }

  @Test
  public void givenRepeatedGameName_whenCreating_thenReceivesError() {
    // given
    GameModel game = GameFactory.create(gameRepository);
    GameDTO repeatedGameDTO = GameBuilder.create(game.getName());

    @SuppressWarnings("null")
    HttpEntity<GameDTO> body = new HttpEntity<>(repeatedGameDTO);

    // when
    ResponseEntity<String> response = restTemplate.exchange(
        "/games", HttpMethod.POST, body, String.class);

    // then
    assertEquals("Game's name already exists", response.getBody());
    assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    assertEquals(1, gameRepository.count());
  }

}
