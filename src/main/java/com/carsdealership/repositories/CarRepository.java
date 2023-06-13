package com.carsdealership.repositories;

import com.carsdealership.models.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT c FROM Car c " +
            "WHERE (:carBrand IS NULL OR c.carBrand = :carBrand) " +
            "AND (:carModel IS NULL OR c.carModel = :carModel) " +
            "AND (:minYear IS NULL OR c.year >= :minYear) " +
            "AND (:maxYear IS NULL OR c.year <= :maxYear) " +
            "AND (:minPrice IS NULL OR c.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR c.price <= :maxPrice)")
    List<Car> searchCars(String carBrand, String carModel, Integer minYear, Integer maxYear,
                         BigDecimal minPrice, BigDecimal maxPrice);
}