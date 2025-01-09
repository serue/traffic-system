package com.beymo.traffic.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public record RoleRequest (
        Integer id,
        @NotNull(message = "Role cannot be null")
        @NotEmpty(message = "The role cannot be empty")
        @NotBlank(message = "Role name must not be blank")
        String name
){

}
