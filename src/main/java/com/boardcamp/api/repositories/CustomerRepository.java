package com.boardcamp.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boardcamp.api.models.CustomerModel;

import lombok.NonNull;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {

  public boolean existsByCpf(@NonNull String id);

}
