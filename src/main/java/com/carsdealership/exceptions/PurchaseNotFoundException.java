package com.carsdealership.exceptions;

public class PurchaseNotFoundException extends IllegalArgumentException {
    public PurchaseNotFoundException(String message) {
        super(message);
    }
}