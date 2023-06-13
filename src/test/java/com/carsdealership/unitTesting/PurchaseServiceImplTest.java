package com.carsdealership.unitTesting;

import com.carsdealership.exceptions.PurchaseNotFoundException;
import com.carsdealership.models.dtos.CarDTO;
import com.carsdealership.models.entities.Car;
import com.carsdealership.repositories.CarRepository;
import com.carsdealership.repositories.CustomerRepository;
import com.carsdealership.repositories.PurchaseRepository;
import com.carsdealership.services.PurchaseServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PurchaseServiceImplTest {
    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private PurchaseRepository purchaseRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private PurchaseServiceImpl purchaseService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        purchaseService = new PurchaseServiceImpl(objectMapper, purchaseRepository, customerRepository, carRepository);
    }

    @Test
    void deletePurchaseById_ExistingPurchaseId_DeletesPurchase() {
        long purchaseId = 1L;
        when(purchaseRepository.existsById(purchaseId)).thenReturn(true);

        assertDoesNotThrow(() -> purchaseService.deletePurchaseById(purchaseId));

        verify(purchaseRepository, times(1)).deleteById(purchaseId);
    }

    @Test
    void deletePurchaseById_NonExistingPurchaseId_ThrowsPurchaseNotFoundException() {
        long purchaseId = 1L;
        when(purchaseRepository.existsById(purchaseId)).thenReturn(false);

        assertThrows(PurchaseNotFoundException.class, () -> purchaseService.deletePurchaseById(purchaseId));
    }
}