package com.beymo.traffic.accused.dto;

import java.time.LocalDate;

public record LicenceResponse (
        Long id,
        String number,
        LocalDate firstIssueDate,
        LocalDate issueDate,
        LocalDate expiryDate,
        String placeOfIssue
){
}
