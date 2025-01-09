package com.beymo.traffic.court.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CourtRequest(
        Long id,
        @NotNull(message = "Court number cannot be null")
        @NotEmpty(message = "Court number cannot be empty")
        @NotBlank(message = "Court number cannot be blank")
        String courtNumber,
        String name,
        @NotNull(message = "Please set the district, court cannot be saved without a district")
        Long districtId
) {
}
