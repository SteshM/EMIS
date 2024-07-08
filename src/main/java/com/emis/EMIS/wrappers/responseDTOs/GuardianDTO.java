package com.emis.EMIS.wrappers.responseDTOs;

import lombok.Data;

@Data
public class GuardianDTO {
    private int guardianId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String nationalId;
    private String phoneNo;
    private String gender;
    private String nationality;
    private String emergencyContact;
    private String relationship;
    private String occupation;
}
