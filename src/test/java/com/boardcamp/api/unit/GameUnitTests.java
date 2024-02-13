package com.boardcamp.api.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.boardcamp.api.builders.GameBuilder;
import com.boardcamp.api.dtos.GameDTO;
import com.boardcamp.api.exceptions.ConflictGameNameException;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.repositories.GameRepository;
import com.boardcamp.api.services.GameService;

@SpringBootTest
public class GameUnitTests {

  @InjectMocks
  private GameService gameService;

  @Mock
  private GameRepository gameRepository;

  @Test
  public void givenRepeatedGameName_whenCreating_thenThrowsError() {
    GameDTO dto = GameBuilder.create();
    doReturn(true).when(gameRepository).existsByName(any());

    ConflictGameNameException exception = assertThrows(
        ConflictGameNameException.class,
        () -> gameService.create(dto));

    assertNotNull(exception);
    assertEquals("Game's name already exists", exception.getMessage());
    verify(gameRepository, times(0)).save(new GameModel(dto));
  }

}
