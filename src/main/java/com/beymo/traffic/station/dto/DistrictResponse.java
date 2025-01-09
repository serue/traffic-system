package com.beymo.traffic.station.dto;

public record DistrictResponse(
        Long id,
        String code,
        String name,
        String province
) {
}
