package com.emis.EMIS.wrappers.responseDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class StudentDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String registrationNo;
    private String gender;
    private String nationality;
    private String dateOfBirth;
}
