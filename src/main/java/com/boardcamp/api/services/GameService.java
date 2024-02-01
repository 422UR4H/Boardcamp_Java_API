package com.boardcamp.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.GameDTO;
import com.boardcamp.api.exceptions.ConflictGameNameException;
import com.boardcamp.api.exceptions.GameNotFoundException;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.repositories.GameRepository;

import lombok.NonNull;

@Service
public class GameService {

  private final GameRepository gameRepository;

  public GameService(GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }

  public List<GameModel> findAll() {
    return gameRepository.findAll();
  }

  public GameModel create(GameDTO dto) {
    if (gameRepository.existsByName(dto.getName())) {
      throw new ConflictGameNameException();
    }
    return gameRepository.save(new GameModel(dto));
  }

  public GameModel findById(@NonNull Long gameId) {
    return gameRepository.findById(gameId).orElseThrow(() -> {
      throw new GameNotFoundException();
    });
  }

}
