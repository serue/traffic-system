package com.beymo.traffic.user.dto;

import com.beymo.traffic.user.model.Rank;
import jakarta.validation.constraints.*;

import java.util.Set;

public record UserRequest(
        @NotEmpty(message = "First name is mandatory")
        @NotBlank(message = "First name is mandatory")
        String firstname,
        @NotEmpty(message = "Last name is mandatory")
        @NotBlank(message = "Last name is mandatory")
        String lastname,
        @NotEmpty(message = "Force number is mandatory")
        @NotBlank(message = "Force number is mandatory")
        String forcenumber,
        @Email(message = "Email is not well formatted")
        @NotEmpty(message = "Email is mandatory")
        @NotBlank(message = "Email is mandatory")
        String email,
        @NotNull(message = "Rank is required")
        Rank rank,
        @NotEmpty(message = "Password is mandatory")
        @NotBlank(message = "Password is mandatory")
        @Size(min = 8, message = "Password is too short, 8 characters minimum is required")
        String password,
        @NotNull(message = "The role is required when registering a user")
        Set<Integer> roleId
) {
}
