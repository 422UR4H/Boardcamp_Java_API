package com.boardcamp.api.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.boardcamp.api.builders.CustomerBuilder;
import com.boardcamp.api.builders.GameBuilder;
import com.boardcamp.api.builders.RentalBuilder;
import com.boardcamp.api.dtos.RentalDTO;
import com.boardcamp.api.exceptions.BadRequestException;
import com.boardcamp.api.exceptions.StockLimitGameRentalException;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.repositories.RentalRepository;
import com.boardcamp.api.services.GameService;
import com.boardcamp.api.services.RentalService;

@SpringBootTest
public class RentalUnitTests {

  @InjectMocks
  private RentalService rentalService;

  @Mock
  private RentalRepository rentalRepository;

  @Mock
  private GameService gameService;

  @SuppressWarnings("null")
  @Test
  public void givendNullGameId_whenCreating_thenThrowsException() {
    // given
    RentalDTO dto = new RentalDTO();
    dto.setDaysRented(0);
    dto.setCustomerId(1L);

    // when
    BadRequestException exception = assertThrows(
        BadRequestException.class,
        () -> rentalService.create(dto));

    // then
    assertNotNull(exception);
    verify(rentalRepository, times(0)).save(any());
  }

  @SuppressWarnings("null")
  @Test
  public void givendNullCustomerId_whenCreating_thenThrowsException() {
    // given
    RentalDTO dto = new RentalDTO();
    dto.setDaysRented(0);
    dto.setGameId(1L);

    // when
    BadRequestException exception = assertThrows(
        BadRequestException.class,
        () -> rentalService.create(dto));

    // then
    assertNotNull(exception);
    verify(rentalRepository, times(0)).save(any());
  }

  @SuppressWarnings("null")
  @Test
  public void givendStockLimitGameRental_whenCreating_thenThrowsException() {
    // given
    final Long ID = 1L;
    RentalDTO dto = RentalBuilder.create();
    CustomerModel customer = new CustomerModel(CustomerBuilder.create());
    GameModel game = new GameModel(GameBuilder.create());

    customer.setId(ID);
    game.setId(ID);
    doReturn(game).when(gameService).findById(anyLong());
    doReturn(game.getStockTotal()).when(rentalRepository).countByGameIdAndReturnDateIsNull(anyLong());

    // when
    StockLimitGameRentalException exception = assertThrows(
      StockLimitGameRentalException.class,
        () -> rentalService.create(dto));

    // then
    assertNotNull(exception);
    assertEquals("Stock limit for renting this game", exception.getMessage());
    verify(rentalRepository, times(0)).save(any());
  }

}
