package com.boardcamp.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.services.GameService;

@RestController
@RequestMapping("games")
public class GameController {

  private final GameService gameService;

  public GameController(GameService gameService) {
    this.gameService = gameService;
  }

  @GetMapping
  public List<GameModel> getAllGames() {
    return gameService.findAllGames();
  }
}
