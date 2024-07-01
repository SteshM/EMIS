package com.emis.EMIS.wrappers.requestDTOs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DirectorsRequestDTO {
    private int schoolId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String nationalId;
    private String gender;
    private String nationality;
    private String dateOfBirth;
    private String phoneNo;
}
