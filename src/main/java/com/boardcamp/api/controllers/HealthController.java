package com.boardcamp.api.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.services.HealthService;

@CrossOrigin("*")
@RestController
@RequestMapping("health")
public class HealthController {

  private final HealthService healthService;

  public HealthController(HealthService healthService) {
    this.healthService = healthService;
  }

  @GetMapping
  public String getHealth() {
    return healthService.getHealth();
  }
}
