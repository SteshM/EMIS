package com.emis.EMIS.wrappers.responseDTOs;

import lombok.Data;

@Data
public class SchoolAdminDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String nationalId;
    private String phoneNo;
    private String adminRole;
    private String department;
    private String officePhone;
    private String tscNumber;
}
