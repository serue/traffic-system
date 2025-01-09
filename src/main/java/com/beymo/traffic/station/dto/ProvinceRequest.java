package com.beymo.traffic.station.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ProvinceRequest(
        Long id,
        @NotNull(message = "The provincial code cannot be null")
        @NotEmpty(message = "The provincial code cannot be empty")
        @NotBlank(message = "The provincial code cannot be blank")
        String code,
        @NotNull(message = "The provincial name cannot be null")
        @NotEmpty(message = "The provincial name cannot be empty")
        @NotBlank(message = "The provincial name cannot be blank")
        String name
) {
}
