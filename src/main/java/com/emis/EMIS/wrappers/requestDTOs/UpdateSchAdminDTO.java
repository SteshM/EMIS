package com.emis.EMIS.wrappers.requestDTOs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateSchAdminDTO {
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
    private String gender;
    private String nationality;
    private String dateOfBirth;
}
