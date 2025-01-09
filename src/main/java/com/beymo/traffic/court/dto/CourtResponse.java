package com.beymo.traffic.court.dto;

public record CourtResponse(
        Long id,
        String courtNumber,
        String name,
        String districtName,
        String provinceName
) {
}
