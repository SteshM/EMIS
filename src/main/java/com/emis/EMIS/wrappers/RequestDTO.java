package com.emis.EMIS.wrappers;

import lombok.Data;

@Data
public class RequestDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private int nationalId;
    private int contactNo;
    private int password;
}
