package com.beymo.traffic.court.dto;

import java.time.LocalDate;

public record ScheduleResponse(
        Long id,
        String courtNumber,
        String district,
        LocalDate startDate,
        LocalDate endDate
) {
}
