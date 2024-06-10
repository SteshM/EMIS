package com.emis.EMIS.wrappers.responseDTOs;

import lombok.Data;

@Data
public class TeacherDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String gender;
    private String nationality;
    private String dateOfBirth;
    private String tscNo;
    private int yearsOfExperience;
}
