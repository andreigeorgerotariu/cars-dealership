package com.carsdealership.exceptions;

public class CarNotFoundException extends IllegalArgumentException{

    public CarNotFoundException(String message) { super(message);}
}