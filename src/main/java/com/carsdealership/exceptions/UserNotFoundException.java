package com.carsdealership.exceptions;

public class UserNotFoundException extends IllegalArgumentException{

    public UserNotFoundException(String message) { super(message);}
}