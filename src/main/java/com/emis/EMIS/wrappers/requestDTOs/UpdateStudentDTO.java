package com.emis.EMIS.wrappers.requestDTOs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateStudentDTO {
    private String schoolName;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String registrationNo;
    private String gender;
    private String nationality;
    private String dateOfBirth;
}
