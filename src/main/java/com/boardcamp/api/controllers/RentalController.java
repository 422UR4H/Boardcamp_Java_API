package com.boardcamp.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.boardcamp.api.dtos.RentalDTO;
import com.boardcamp.api.models.RentalModel;
import com.boardcamp.api.services.RentalService;

import jakarta.validation.Valid;

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

  @PostMapping()
  public RentalModel postRental(@RequestBody @Valid RentalDTO dto) {
    return rentalService.create(dto);
  }

  @PutMapping("/{id}/return")
  public RentalModel finish(@PathVariable("id") Long id) {
    return rentalService.finish(id);
  }

}
