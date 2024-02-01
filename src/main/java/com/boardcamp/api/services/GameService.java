package com.boardcamp.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.CreateGameDTO;
import com.boardcamp.api.exceptions.ConflictGameNameException;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.repositories.GameRepository;

@Service
public class GameService {

  private final GameRepository gameRepository;

  public GameService(GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }

  public List<GameModel> findAllGames() {
    return gameRepository.findAll();
  }

  public GameModel create(CreateGameDTO dto) {
    if (gameRepository.existsByName(dto.getName())) {
      throw new ConflictGameNameException();
    }
    return gameRepository.save(new GameModel(dto));
  }

}
