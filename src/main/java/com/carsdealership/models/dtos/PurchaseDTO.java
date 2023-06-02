package com.carsdealership.models.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@ToString
public class PurchaseDTO implements Serializable {

    private long id;
    @NotNull(message = "Purchase date must not be blank")
    private LocalDateTime purchaseDate;
    @Positive(message = "Total price must be a positive value")
    private double totalPrice;
    @Pattern(regexp = "^(cash|card)$", message = "Payment method must be either 'cash' or 'card'")
    private String paymentMethod;
}