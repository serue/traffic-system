package com.beymo.traffic.user.dto;

import com.beymo.traffic.role.Role;
import com.beymo.traffic.user.model.Rank;

import java.time.LocalDate;
import java.util.List;

public record UserResponse(
        Integer id,
        String firstname,
        String lastname,
        String forcenumber,
        String email,
        Rank rank,
        LocalDate dateOfBirth,
        List<Role> roles,
        boolean enabled,
        boolean accountLocked

) {
}
