package com.beymo.traffic.station.dto;

public record StationResponse(
        Long id,
        String code,
        String name,
        String district,
        String province
) {
}
