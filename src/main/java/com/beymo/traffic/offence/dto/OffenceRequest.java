package com.beymo.traffic.offence.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record OffenceRequest(
        Long id,
        @NotNull(message = "The offence section is required")
        @NotEmpty(message = "The offence section cannot be empty")
        @NotBlank(message = "The offence section cannot be blank")
        String section,
        @NotNull(message = "The offence title is required")
        @NotEmpty(message = "The offence title cannot be empty")
        @NotBlank(message = "The offence title cannot be blank")
        String title,
        @NotNull(message = "Fine is cannot be null")
        @PositiveOrZero(message = "fine must be greater than or equal to zero")
        Double fine
) {
}
