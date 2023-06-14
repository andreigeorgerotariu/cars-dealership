package com.carsdealership.repositories;

import com.carsdealership.models.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    @Query("SELECT DISTINCT p FROM Purchase p JOIN FETCH p.cars")
    List<Purchase> findAllWithCars();
}