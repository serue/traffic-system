package com.beymo.traffic.offence.dto;

import java.math.BigDecimal;

public record OffenceResponse(
        Long id,
        String section,
        String title,
        Double fine
) {
}
