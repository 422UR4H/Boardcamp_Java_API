package com.boardcamp.api.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "rentals")
public class RentalModel {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "rental_customer", joinColumns = @JoinColumn(name = "rental_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"))
  private List<CustomerModel> customers = new ArrayList<CustomerModel>();

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "rental_game", joinColumns = @JoinColumn(name = "rental_id"), inverseJoinColumns = @JoinColumn(name = "game_id"))
  private List<GameModel> games = new ArrayList<GameModel>();

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
    this.customers.add(customer);
    this.games.add(game);
    this.rentDate = LocalDate.now();
    this.returnDate = null;
    this.daysRented = daysRented;
    this.originalPrice = daysRented * pricePerDay;
    this.delayFee = 0;
  }

}
