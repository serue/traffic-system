package com.beymo.traffic.accused.dto;

import com.beymo.traffic.accused.model.Gender;
import com.beymo.traffic.accused.model.PortOfEntry;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AccusedResponse (
        Long id,
        String firstname,
        String lastname,
        String email,
        String phone,
        String address,
        String businessAddress,
        String city,
        String birthplace,
        LocalDate dob,
        Gender gender,
        String country,
        // the date the accused entered into the country if not a citizen
        LocalDate entryDate,
        PortOfEntry port,
        String licenceNumber,
        LocalDate firstIssueDate,
        LocalDate issueDate,
        LocalDate expiryDate,
        String placeOfIssue
){
}
