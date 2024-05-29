package com.emis.EMIS.wrappers.requestDTOs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ActivateAccDTO {
    private String otp;
    private String email;
    private String password;
}
