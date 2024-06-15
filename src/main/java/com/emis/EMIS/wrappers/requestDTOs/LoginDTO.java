package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank(message = "Email is mandatory!")
    private String email;
    @NotNull(message = "This field cannot be null")
    @Size(min = 5,max=8)
    private String password;
}