package com.carsdealership.services;

import com.carsdealership.models.dtos.PurchaseDTO;

public interface PurchaseService {

    PurchaseDTO createPurchase(PurchaseDTO purchaseDTO);
}