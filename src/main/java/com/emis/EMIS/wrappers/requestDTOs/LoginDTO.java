package com.emis.EMIS.wrappers.requestDTOs;

import lombok.Data;

@Data
public class LoginDTO {
    private String email;
    private String password;
}