package com.beymo.traffic.court.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ScheduleRequest(
        Long id,
        @NotNull(message = "Court cannot be null")
        Long courtId,
        @NotNull(message = "Starting date is required")
        LocalDate startDate,
        @NotNull(message = "End Date is required")
        LocalDate endDate
) {
}
