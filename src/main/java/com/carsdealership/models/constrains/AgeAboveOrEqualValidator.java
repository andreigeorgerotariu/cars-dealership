package com.carsdealership.models.constrains;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class AgeAboveOrEqualValidator implements ConstraintValidator<AgeAboveOrEqual, Integer> {
    private static final int MIN_AGE = 18;

    @Override
    public boolean isValid(Integer yearOfBirth, ConstraintValidatorContext context) {
        if (yearOfBirth == null) {
            return false;
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate birthDate = LocalDate.of(yearOfBirth, 1, 1);
        Period age = Period.between(birthDate, currentDate);

        return age.getYears() >= MIN_AGE;
    }
}