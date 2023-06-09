package com.carsdealership.services;

import com.carsdealership.exceptions.CarNotFoundException;
import com.carsdealership.exceptions.CustomerNotFoundException;
import com.carsdealership.exceptions.PurchaseNotFoundException;
import com.carsdealership.models.dtos.CarDTO;
import com.carsdealership.models.dtos.CarIdDTO;
import com.carsdealership.models.dtos.PurchaseDTO;
import com.carsdealership.models.dtos.PurchaseResponseDTO;
import com.carsdealership.models.entities.Car;
import com.carsdealership.models.entities.Customer;
import com.carsdealership.models.entities.Purchase;
import com.carsdealership.repositories.CarRepository;
import com.carsdealership.repositories.CustomerRepository;
import com.carsdealership.repositories.PurchaseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final ObjectMapper objectMapper;
    private final PurchaseRepository purchaseRepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;

    @Autowired
    public PurchaseServiceImpl(ObjectMapper objectMapper, PurchaseRepository purchaseRepository, CustomerRepository customerRepository, CarRepository carRepository) {
        this.objectMapper = objectMapper;
        this.purchaseRepository = purchaseRepository;
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public PurchaseResponseDTO createPurchase(PurchaseDTO purchaseDTO) {
        Purchase purchase = objectMapper.convertValue(purchaseDTO, Purchase.class);
        Customer customer = customerRepository.findById(purchaseDTO.getCustomerId()).orElseThrow(() -> new CustomerNotFoundException("Customer not found."));
        Set<Car> cars = new HashSet<>();
        Set<CarDTO> carDTOList = new HashSet<>();
        double totalPrice = 0;

        for (CarIdDTO carIdDTO : purchaseDTO.getPurchaseCarIdList()) {
            Car car = carRepository.findById(carIdDTO.getId()).orElseThrow(() -> new CarNotFoundException("Car not found."));
            cars.add(car);
            CarDTO carDTO = objectMapper.convertValue(car, CarDTO.class);
            carDTOList.add(carDTO);
            totalPrice += car.getPrice();
        }

        purchase.setCustomer(customer);
        purchase.setCars(cars);
        purchase.setPurchaseDate(LocalDateTime.now());
        purchase.setTotalPrice(totalPrice);
        Purchase savedPurchase = purchaseRepository.save(purchase);
        log.info("Purchase with id " + savedPurchase.getId() + " made at " + savedPurchase.getPurchaseDate() + " was successfully created.");
        return getPurchaseResponseDTO(carDTOList, totalPrice, savedPurchase);
    }

    private PurchaseResponseDTO getPurchaseResponseDTO(Set<CarDTO> carDTOList, double totalPrice, Purchase savedPurchase) {
        PurchaseResponseDTO purchaseResponseDTO = new PurchaseResponseDTO();
        purchaseResponseDTO.setId(savedPurchase.getId());
        purchaseResponseDTO.setCustomerId(savedPurchase.getCustomer().getId());
        purchaseResponseDTO.setPurchaseDate(LocalDateTime.now());
        purchaseResponseDTO.setTotalPrice(totalPrice);
        purchaseResponseDTO.setPaymentMethod(savedPurchase.getPaymentMethod());
        purchaseResponseDTO.setCarList(carDTOList);
        return purchaseResponseDTO;
    }

    @Transactional(readOnly = true)
    @Override
    public List<PurchaseResponseDTO> getAllPurchases() {
        List<Purchase> purchasesFound = purchaseRepository.findAllWithCars();
        List<PurchaseResponseDTO> purchasesFoundDTO = new ArrayList<>();
        for (Purchase purchase : purchasesFound) {

            PurchaseResponseDTO purchaseResponseDTO = new PurchaseResponseDTO();
            purchaseResponseDTO.setId(purchase.getId());
            purchaseResponseDTO.setCustomerId(purchase.getCustomer().getId());
            purchaseResponseDTO.setPurchaseDate(LocalDateTime.now());
            purchaseResponseDTO.setTotalPrice(purchase.getTotalPrice());
            purchaseResponseDTO.setPaymentMethod(purchase.getPaymentMethod());

            Set<CarDTO> carDTOSet = new HashSet<>();

            for (Car car : purchase.getCars()) {
                CarDTO carDTO = new CarDTO();
                carDTO.setId(car.getId());
                carDTO.setCarBrand(car.getCarBrand());
                carDTO.setCarModel(car.getCarModel());
                carDTO.setYear(car.getYear());
                carDTO.setPrice(car.getPrice());
                carDTOSet.add(carDTO);
            }
            purchasesFoundDTO.add(purchaseResponseDTO);
            purchaseResponseDTO.setCarList(carDTOSet);
        }
        return purchasesFoundDTO;
    }

    @Override
    public void deletePurchaseById(long id) {
        if (purchaseRepository.existsById(id)) {
            purchaseRepository.deleteById(id);
            log.info("Purchase with id " + id + " was successfully deleted.");
        } else {
            throw new PurchaseNotFoundException("Purchase not found.");
        }
    }
}