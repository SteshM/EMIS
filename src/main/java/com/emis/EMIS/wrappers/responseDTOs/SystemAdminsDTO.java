package com.emis.EMIS.wrappers.responseDTOs;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SystemAdminsDTO {
//    @NotEmpty(message = "System admin id is required!")
//    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String nationalId;
    private String phoneNo;
    private String employmentNo;
    private String department;
    private String officePhoneNo;
}
