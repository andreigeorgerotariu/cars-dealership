package com.carsdealership.controllers;

import com.carsdealership.models.dtos.CarDTO;
import com.carsdealership.services.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<CarDTO>> getAllCars(){
        return ResponseEntity.ok(carService.getAllCars());
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<Void> deleteCarById(@PathVariable long carId){
        carService.deleteCarById(carId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{carId}")
    public ResponseEntity<CarDTO> updateCarById(@PathVariable long carId, @RequestBody @Valid CarDTO carDTO){
        return ResponseEntity.ok(carService.updateCarById(carId, carDTO));
    }
}