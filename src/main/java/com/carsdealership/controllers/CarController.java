package com.carsdealership.controllers;

import com.carsdealership.models.dtos.CarDTO;
import com.carsdealership.services.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<CarDTO> createCar(@RequestBody @Valid CarDTO carDTO) {
        return ResponseEntity.ok(carService.createCar(carDTO));
    }

    @GetMapping
    public ResponseEntity<List<CarDTO>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping("/search")
    public ResponseEntity<List<CarDTO>> findCarByCarBrandAndCarModelAndYearAndPrice(@RequestParam(required = false) String carBrand,
                                                                                    @RequestParam(required = false) String carModel,
                                                                                    @RequestParam(required = false) Integer year,
                                                                                    @RequestParam(required = false) BigDecimal minPrice,
                                                                                    @RequestParam(required = false) BigDecimal maxPrice) {
        List<CarDTO> searchedCars = carService.findCarByCarBrandAndCarModelAndYearAndPrice(carBrand, carModel, year, minPrice, maxPrice);
        return ResponseEntity.ok(searchedCars);
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<Void> deleteCarById(@PathVariable long carId) {
        carService.deleteCarById(carId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{carId}")
    public ResponseEntity<CarDTO> updateCarById(@PathVariable long carId, @RequestBody @Valid CarDTO carDTO) {
        return ResponseEntity.ok(carService.updateCarById(carId, carDTO));
    }
}