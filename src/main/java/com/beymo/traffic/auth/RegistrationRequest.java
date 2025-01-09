package com.beymo.traffic.auth;

import com.beymo.traffic.user.model.Rank;
import jakarta.validation.constraints.*;


public class RegistrationRequest {
    @NotEmpty(message = "First name is mandatory")
    @NotBlank(message = "First name is mandatory")
    private String firstname;
    @NotEmpty(message = "Last name is mandatory")
    @NotBlank(message = "Last name is mandatory")
    private String lastname;
    @NotEmpty(message = "Force number is mandatory")
    @NotBlank(message = "Force number is mandatory")
    private String forcenumber;
    @Email(message = "Email is not well formatted")
    @NotEmpty(message = "Email is mandatory")
    @NotBlank(message = "Email is mandatory")
    private String email;
    @NotNull(message = "Rank is required")
    private Rank rank;
    @NotEmpty(message = "Password is mandatory")
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password is too short, 8 characters minimum is required")
    private String password;


    public  String getFirstname() {
        return firstname;
    }

    public void setFirstname( String firstname) {
        this.firstname = firstname;
    }

    public  String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getForcenumber() {
        return forcenumber;
    }

    public void setForcenumber(String forcenumber) {
        this.forcenumber = forcenumber;
    }

    public  Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }
}
