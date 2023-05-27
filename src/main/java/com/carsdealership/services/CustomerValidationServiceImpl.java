package com.carsdealership.services;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class CustomerValidationServiceImpl implements CustomerValidationService {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @Override
    public void emailValidation(String email) {
        if (EMAIL_PATTERN.matcher(email).matches()) {
            throw new RuntimeException("Email format is not valid");
        }
    }
}