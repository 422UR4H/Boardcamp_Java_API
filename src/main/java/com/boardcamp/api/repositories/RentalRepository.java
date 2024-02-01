package com.boardcamp.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.boardcamp.api.models.RentalModel;

@Repository
public interface RentalRepository extends JpaRepository<RentalModel, Long> {
  @Query(value = "SELECT COUNT(*) " +
      "FROM rental_game AS rg " +
      "JOIN rentals AS r ON r.id = rg.rental_id " +
      "WHERE r.return_date IS NULL AND rg.game_id = :gameId", nativeQuery = true)
  public int countByGameId(@Param("gameId") Long gameId);
}
