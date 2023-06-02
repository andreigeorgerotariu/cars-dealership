package com.carsdealership.repositories;

import com.carsdealership.models.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT c FROM Car c WHERE c.carBrand = :carBrand AND c.carModel = :carModel AND c.year = :year AND c.price >= :minPrice AND c.price <= :maxPrice")
    List<Car> findCarByCarBrandAndCarModelAndYearAndPrice(@Param("carBrand") String carBrand,
                                                          @Param("carModel") String carModel,
                                                          @Param("year") Integer year,
                                                          @Param("minPrice") BigDecimal minPrice,
                                                          @Param("maxPrice") BigDecimal maxPrice);

    List<Car> findCarByCarBrandAndCarModelAndYear(String carBrand, String carModel, Integer year);
    List<Car> findCarByCarBrandAndCarModel(String carBrand, String carModel);
    List<Car> findCarByCarBrand(String carBrand);
    List<Car> findCarByYear(Integer year);
}