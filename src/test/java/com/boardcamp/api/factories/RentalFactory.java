package com.boardcamp.api.factories;

// import com.boardcamp.api.builders.RentalBuilder;
// import com.boardcamp.api.dtos.RentalDTO;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.models.RentalModel;
import com.boardcamp.api.repositories.CustomerRepository;
import com.boardcamp.api.repositories.GameRepository;
import com.boardcamp.api.repositories.RentalRepository;

public class RentalFactory {

  public static RentalModel create(RentalRepository rentalRepository,
      CustomerRepository customerRepository, GameRepository gameRepository) {

    CustomerModel customer = CustomerFactory.create(customerRepository);
    GameModel game = GameFactory.create(gameRepository);

    // RentalDTO dto = RentalBuilder.create(customer.getId(), game.getId());
    RentalModel rental = new RentalModel(
        1,
        game.getPricePerDay(),
        customer,
        game);

    return rentalRepository.save(rental);
  }

}
