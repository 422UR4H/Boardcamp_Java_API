package com.boardcamp.api.models;

import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rentals")
public class RentalModel {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(nullable = false)
  private Long customerId;

  @Column(nullable = false)
  private Long gameId;

  @Column(nullable = false)
  private LocalDate rentDate;

  @Column(nullable = false)
  private int daysRented;

  @Column
  private LocalDate returnDate;

  @Column(nullable = false)
  private int originalPrice;

  @Column(nullable = false)
  @ColumnDefault("0")
  private int delayFee;

}
