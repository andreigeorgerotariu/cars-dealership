package com.carsdealership.unitTesting;

import com.carsdealership.exceptions.CarNotFoundException;
import com.carsdealership.models.dtos.CarDTO;
import com.carsdealership.models.entities.Car;
import com.carsdealership.repositories.CarRepository;
import com.carsdealership.services.CarServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarServiceImplTest {
    @Mock
    private CarRepository carRepository;

    @Mock
    private ObjectMapper objectMapper;

    private CarServiceImpl carService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        carService = new CarServiceImpl(objectMapper, carRepository);
    }

    @Test
    void TestCreateCar_ValidCarDTO_ReturnsCreatedCarDTO() {
        CarDTO carDTO = new CarDTO();
        carDTO.setCarBrand("Toyota");
        carDTO.setCarModel("Camry");
        carDTO.setYear(2022);
        carDTO.setPrice(25000);

        Car car = new Car();
        car.setCarBrand("Toyota");
        car.setCarModel("Camry");
        car.setYear(2022);
        car.setPrice(25000);

        when(objectMapper.convertValue(carDTO, Car.class)).thenReturn(car);
        when(carRepository.save(car)).thenReturn(car);
        when(objectMapper.convertValue(car, CarDTO.class)).thenReturn(carDTO);

        CarDTO createdCarDTO = carService.createCar(carDTO);

        assertNotNull(createdCarDTO);
        assertEquals(carDTO.getCarBrand(), createdCarDTO.getCarBrand());
        assertEquals(carDTO.getCarModel(), createdCarDTO.getCarModel());
        assertEquals(carDTO.getYear(), createdCarDTO.getYear());
        assertEquals(carDTO.getPrice(), createdCarDTO.getPrice());
        verify(carRepository, times(1)).save(car);
    }

    @Test
    void TestCreateCar_EmptyCarDTO_ThrowsException() {
        CarDTO carDTO = new CarDTO();

        assertThrows(Exception.class, () -> carService.createCar(carDTO));
    }

    @Test
    void TestGetAllCars_ReturnsAllCarsDTO() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car());
        cars.add(new Car());

        List<CarDTO> expectedCarsDTO = new ArrayList<>();
        expectedCarsDTO.add(new CarDTO());
        expectedCarsDTO.add(new CarDTO());

        when(carRepository.findAll()).thenReturn(cars);
        when(objectMapper.convertValue(cars.get(0), CarDTO.class)).thenReturn(expectedCarsDTO.get(0));
        when(objectMapper.convertValue(cars.get(1), CarDTO.class)).thenReturn(expectedCarsDTO.get(1));

        List<CarDTO> allCarsDTO = carService.getAllCars();

        assertNotNull(allCarsDTO);
        assertEquals(expectedCarsDTO.size(), allCarsDTO.size());
    }

    @Test
    void TestDeleteCarById_ExistingCarId_DeletesCar() {
        long carId = 1L;
        when(carRepository.existsById(carId)).thenReturn(true);

        assertDoesNotThrow(() -> carService.deleteCarById(carId));
        verify(carRepository, times(1)).deleteById(carId);
    }

    @Test
    void TestDeleteCarById_NonExistingCarId_ThrowsCarNotFoundException() {
        long carId = 1L;
        when(carRepository.existsById(carId)).thenReturn(false);

        assertThrows(CarNotFoundException.class, () -> carService.deleteCarById(carId));
        verify(carRepository, never()).deleteById(carId);
    }


    @Test
    void TestUpdateCarById_NonExistingCarId_ThrowsCarNotFoundException() {
        long carId = 1L;
        CarDTO updatedCarDTO = new CarDTO();
        updatedCarDTO.setCarBrand("Toyota");
        updatedCarDTO.setCarModel("Camry");
        updatedCarDTO.setYear(2023);
        updatedCarDTO.setPrice(30000);

        when(carRepository.findById(carId)).thenReturn(Optional.empty());

        assertThrows(CarNotFoundException.class, () -> carService.updateCarById(carId, updatedCarDTO));
        verify(carRepository, times(1)).findById(carId);
        verify(carRepository, never()).save(any());
    }

    @Test
    void TestSearchCars_ValidParameters_ReturnsMatchingCarsDTO() {
        String carBrand = "Toyota";
        String carModel = "Camry";
        Integer minYear = 2020;
        Integer maxYear = 2022;
        BigDecimal minPrice = BigDecimal.valueOf(20000);
        BigDecimal maxPrice = BigDecimal.valueOf(30000);

        List<Car> matchingCars = new ArrayList<>();
        matchingCars.add(new Car());
        matchingCars.add(new Car());

        List<CarDTO> expectedMatchingCarsDTO = new ArrayList<>();
        expectedMatchingCarsDTO.add(new CarDTO());
        expectedMatchingCarsDTO.add(new CarDTO());

        when(carRepository.searchCars(carBrand, carModel, minYear, maxYear, minPrice, maxPrice)).thenReturn(matchingCars);
        when(objectMapper.convertValue(matchingCars.get(0), CarDTO.class)).thenReturn(expectedMatchingCarsDTO.get(0));
        when(objectMapper.convertValue(matchingCars.get(1), CarDTO.class)).thenReturn(expectedMatchingCarsDTO.get(1));

        List<CarDTO> resultCarsDTO = carService.searchCars(carBrand, carModel, minYear, maxYear, minPrice, maxPrice);
        assertNotNull(resultCarsDTO);
        assertEquals(expectedMatchingCarsDTO.size(), resultCarsDTO.size());
        verify(carRepository, times(1)).searchCars(carBrand, carModel, minYear, maxYear, minPrice, maxPrice);
    }
}