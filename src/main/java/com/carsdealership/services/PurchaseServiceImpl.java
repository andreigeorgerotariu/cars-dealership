package com.carsdealership.services;

import com.carsdealership.models.dtos.PurchaseDTO;
import com.carsdealership.models.entities.Purchase;
import com.carsdealership.repositories.PurchaseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Slf4j
@Service
public class PurchaseServiceImpl implements PurchaseService{

    private final ObjectMapper objectMapper;
    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseServiceImpl(ObjectMapper objectMapper, PurchaseRepository purchaseRepository) {
        this.objectMapper = objectMapper;
        this.purchaseRepository = purchaseRepository;
    }

    public PurchaseDTO createPurchase(PurchaseDTO purchaseDTO){
        Purchase purchase = objectMapper.convertValue(purchaseDTO, Purchase.class);
        purchase.setPurchaseDate(purchaseDTO.getPurchaseDate());
        Purchase savedPurchase = purchaseRepository.save(purchase);
        log.info("Purchase with id " + savedPurchase.getId() + " made at " + savedPurchase.getPurchaseDate()+ " was successfully created.");
        return objectMapper.convertValue(savedPurchase, PurchaseDTO.class);
    }
}
