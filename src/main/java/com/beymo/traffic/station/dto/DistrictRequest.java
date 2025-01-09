package com.beymo.traffic.station.dto;

import com.beymo.traffic.station.model.Province;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DistrictRequest(
        Long id,
        @NotNull(message = "The district code cannot be null")
        @NotEmpty(message = "The district code cannot be empty")
        @NotBlank(message = "The district code cannot be blank")
        String code,
        @NotNull(message = "The district name cannot be null")
        @NotEmpty(message = "The district name cannot be empty")
        @NotBlank(message = "The district name cannot be blank")
        String name,
        @NotNull(message = "Province must not be null")
        Long provinceId
) {
}
