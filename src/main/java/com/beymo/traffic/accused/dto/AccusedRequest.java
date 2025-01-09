package com.beymo.traffic.accused.dto;

import com.beymo.traffic.accused.model.Gender;
import com.beymo.traffic.accused.model.Licence;
import com.beymo.traffic.accused.model.PortOfEntry;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

public record AccusedRequest(
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
        PortOfEntry port
) {
}
