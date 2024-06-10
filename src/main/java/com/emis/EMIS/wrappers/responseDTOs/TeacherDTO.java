package com.emis.EMIS.wrappers.responseDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
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
