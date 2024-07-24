package com.emis.EMIS.wrappers.requestDTOs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateTeacherDTO {
    private String schoolName;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNo;
    private String nationalId;
    private String email;
    private String gender;
    private String nationality;
    private String dateOfBirth;
    private String tscNo;
    private int yearsOfExperience;
}
