package com.carsdealership.models.dtos;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarIdDTO implements Serializable {

    private long id;
}