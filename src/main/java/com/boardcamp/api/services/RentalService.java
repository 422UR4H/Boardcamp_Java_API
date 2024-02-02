package com.boardcamp.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.RentalDTO;
import com.boardcamp.api.exceptions.BadRequestException;
import com.boardcamp.api.exceptions.RentalFinishedException;
import com.boardcamp.api.exceptions.RentalNotFoundException;
import com.boardcamp.api.exceptions.StockLimitGameRentalException;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.models.RentalModel;
import com.boardcamp.api.repositories.RentalRepository;

import lombok.NonNull;

@Service
public class RentalService {

  private final RentalRepository rentalRepository;
  private final CustomerService customerService;
  private final GameService gameService;

  public RentalService(RentalRepository rentalRepository, CustomerService customerService, GameService gameService) {
    this.rentalRepository = rentalRepository;
    this.customerService = customerService;
    this.gameService = gameService;
  }

  public List<RentalModel> findAll() {
    return rentalRepository.findAll();
  }

  public RentalModel create(RentalDTO dto) {
    if (dto.getGameId() == null || dto.getCustomerId() == null) {
      throw new BadRequestException();
    }
    GameModel game = gameService.findById(dto.getGameId());
    int openRentals = rentalRepository.countByGameIdAndReturnDateIsNull(game.getId());

    if (openRentals >= game.getStockTotal()) {
      throw new StockLimitGameRentalException();
    }
    CustomerModel customer = customerService.findById(dto.getCustomerId());
    RentalModel rental = new RentalModel(dto.getDaysRented(), game.getPricePerDay(), customer, game);

    return rentalRepository.save(rental);
  }

  public RentalModel finish(@NonNull Long id) {
    RentalModel rental = rentalRepository.findById(id).orElseThrow(() -> {
      throw new RentalNotFoundException();
    });
    if (rental.getReturnDate() != null) {
      throw new RentalFinishedException();
    }
    rental.finish(rental.getGame().getPricePerDay());

    return rentalRepository.save(rental);
  }
}
