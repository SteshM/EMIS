package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ActivateAccDTO {
    @NotNull(message = "An otp is required!")
    @Size(min = 4, message = "Otp must have 4 characters")
    private String otp;
    @NotNull(message = "Email is mandatory!")
    private String email;
    @NotNull(message = "This field cannot be null")
    @Size(min = 5,max=8)
    private String password;
}
