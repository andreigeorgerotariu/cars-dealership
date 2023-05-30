package com.carsdealership.models.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = com.carsdealership.models.constraints.YearRangeValidator.class)
@Documented
public @interface YearRange {
    String message() default "Invalid year";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}