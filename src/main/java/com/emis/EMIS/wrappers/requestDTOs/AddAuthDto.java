package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddAuthDto {
    @NotNull(message = "email cannot be null")
    @Email
    @Column (unique = true)
    private String email;
    @NotNull(message = "roleId cannot be null")
    private int roleId;
}
