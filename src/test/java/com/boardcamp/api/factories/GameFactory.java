package com.boardcamp.api.factories;

import com.boardcamp.api.builders.GameBuilder;
import com.boardcamp.api.dtos.GameDTO;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.repositories.GameRepository;

public class GameFactory {

  public static GameModel create(GameRepository gameRepository) {
    GameDTO dto = GameBuilder.create();
    return gameRepository.save(new GameModel(dto));
  }

  public static GameModel create(GameRepository gameRepository, String name) {
    GameDTO dto = GameBuilder.create(name);
    return gameRepository.save(new GameModel(dto));
  }

}
