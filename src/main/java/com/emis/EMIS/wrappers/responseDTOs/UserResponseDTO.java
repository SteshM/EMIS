package com.emis.EMIS.wrappers.responseDTOs;

import lombok.Data;

@Data
public class UserResponseDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private int nationalId;
    private int contactNo;
}
