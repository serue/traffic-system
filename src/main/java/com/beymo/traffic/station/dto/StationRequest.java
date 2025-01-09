package com.beymo.traffic.station.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record StationRequest(
        Long id,
        @NotNull(message = "The station code cannot be null")
        @NotEmpty(message = "The station code cannot be empty")
        @NotBlank(message = "The station code cannot be blank")
        String code,
        @NotNull(message = "The station name cannot be null")
        @NotEmpty(message = "The station name cannot be empty")
        @NotBlank(message = "The station name cannot be blank")
        String name,
        @NotNull(message = "District must not be null")
        Long districtId
) {
}
