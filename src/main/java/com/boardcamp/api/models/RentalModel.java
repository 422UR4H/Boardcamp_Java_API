package com.boardcamp.api.models;

import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "rentals")
public class RentalModel {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "customerId", nullable = false)
  private CustomerModel customer;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "gameId", nullable = false)
  private GameModel game;

  @Column(nullable = false)
  private LocalDate rentDate;

  @Column
  private LocalDate returnDate;

  @Column(nullable = false)
  private int daysRented;

  @Column(nullable = false)
  private int originalPrice;

  @Column(nullable = false)
  @ColumnDefault("0")
  private int delayFee;

  public RentalModel(int daysRented, int pricePerDay, CustomerModel customer, GameModel game) {
    this.customer = customer;
    this.game = game;
    this.rentDate = LocalDate.now();
    this.returnDate = null;
    this.daysRented = daysRented;
    this.originalPrice = daysRented * pricePerDay;
    this.delayFee = 0;
  }

}