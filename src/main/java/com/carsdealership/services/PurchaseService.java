package com.carsdealership.services;

import com.carsdealership.models.dtos.PurchaseDTO;
import com.carsdealership.models.dtos.PurchaseResponseDTO;

import java.util.List;

public interface PurchaseService {

    PurchaseResponseDTO createPurchase(PurchaseDTO purchaseDTO);

    List<PurchaseResponseDTO> getAllPurchases();

    void deletePurchaseById(long purchaseId);
}