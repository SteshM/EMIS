package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProfileDto {
    @NotNull(message = "profile is required!")
    private String profile;
}
