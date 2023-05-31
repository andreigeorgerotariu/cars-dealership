package com.carsdealership.models.dtos;

import jakarta.validation.constraints.NotNull;
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
}