package com.boardcamp.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.boardcamp.api.models.RentalModel;
import com.boardcamp.api.services.RentalService;

import java.util.List;

@RestController
@RequestMapping("rentals")
public class RentalController {

  private final RentalService rentalService;

  public RentalController(RentalService rentalService) {
    this.rentalService = rentalService;
  }

  @GetMapping
  public List<RentalModel> getAllRentals() {
    return rentalService.findAll();
  }

}
