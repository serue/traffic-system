package com.beymo.traffic.vehicle.dto;

public record VehicleRequest(
        Long id,
        String registration,
        String model,
        String color
) {
}
