package com.beymo.traffic.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


public class AuthenticationRequest {
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password is too short, 8 characters minimum is required")
    private String password;
    @NotEmpty(message = "Force number is mandatory")
    @NotBlank(message = "Force number is mandatory")
    private String forcenumber;


    public String getPassword() {
        return password;
    }
    public String getForcenumber() {
        return forcenumber;
    }
}
