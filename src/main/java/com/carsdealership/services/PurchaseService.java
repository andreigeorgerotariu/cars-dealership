package com.carsdealership.services;

import com.carsdealership.models.dtos.PurchaseDTO;

import java.util.List;

public interface PurchaseService {

    PurchaseDTO createPurchase(PurchaseDTO purchaseDTO);

    List<PurchaseDTO> getAllPurchases();
}