package com.beymo.traffic.accused.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record LicenceRequest (

        Long id,
        @NotNull(message = "licence number cannot be null")
        @NotEmpty(message = "licence number cannot be empty")
        @NotBlank(message = "licence number cannot be empty")
        String number,
        @NotNull(message = "First issue date cannot be null")
        LocalDate firstIssueDate,
        @NotNull(message = "Issue date cannot be null")
        LocalDate issueDate,
        @NotNull(message = "Expiry date cannot be null")
        LocalDate expiryDate,
        @NotNull(message = "Place of issue cannot be null")
        @NotEmpty(message = "Place of issue cannot be empty")
        @NotBlank(message = "Place of issue cannot be blank")
        String placeOfIssue
){
}
