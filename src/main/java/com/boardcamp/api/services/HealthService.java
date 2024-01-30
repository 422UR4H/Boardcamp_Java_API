package com.boardcamp.api.services;

import org.springframework.stereotype.Service;

@Service
public class HealthService {
  public final String getHealth() {
    return "I'm Ok!";
  }
}
