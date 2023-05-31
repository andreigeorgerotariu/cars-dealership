package com.carsdealership.controllers;

import com.carsdealership.models.dtos.PurchaseDTO;
import com.carsdealership.services.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public ResponseEntity<PurchaseDTO> createPurchase(@RequestBody @Valid PurchaseDTO purchaseDTO){
        return ResponseEntity.ok(purchaseService.createPurchase(purchaseDTO));
    }
}
