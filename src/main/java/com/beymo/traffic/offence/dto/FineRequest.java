package com.beymo.traffic.offence.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record FineRequest(
        Long id,
        @NotNull(message = "Fine is cannot be null")
        @PositiveOrZero(message = "fine must be greater than or equal to zero")
        Double fine
) {
}
