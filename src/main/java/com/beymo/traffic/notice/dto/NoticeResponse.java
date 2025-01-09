package com.beymo.traffic.notice.dto;

import com.beymo.traffic.accused.model.Gender;
import com.beymo.traffic.accused.model.PortOfEntry;

import java.time.LocalDate;

public record NoticeResponse(
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
        String licencenumber,
        LocalDate firstIssueDate,
        LocalDate issueDate,
        LocalDate expiryDate,
        String placeOfIssue,

        String registration,
        String model,
        String color,

        String section,
        String title,
        Double fine,
        String forceNumber,
        String username,

        String courtNumber

) {
}
