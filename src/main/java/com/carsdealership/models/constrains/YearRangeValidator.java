package com.carsdealership.models.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class YearRangeValidator implements ConstraintValidator<com.carsdealership.models.constraints.YearRange, Integer> {
    private final int minYear = 1960;
    private final int currentYear = LocalDate.now().getYear();

    @Override
    public boolean isValid(Integer year, ConstraintValidatorContext context) {
        return year == null || (year >= minYear && year <= currentYear);
    }
}