package com.emis.EMIS.wrappers.responseDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    private String gender;
    private String nationality;
    private String dateOfBirth;
}
