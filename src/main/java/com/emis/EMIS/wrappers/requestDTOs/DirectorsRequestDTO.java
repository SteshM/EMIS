package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DirectorsRequestDTO {
    @NotNull(message = "schoolId is required")
    private int schoolId;
    @NotNull(message = "firstName is required")
    private String firstName;
    @NotNull(message = "middleName is required")
    private String middleName;
    @NotNull(message = "lastName is required")
    private String lastName;
    @NotNull(message = "email is required")
    private String email;
    @NotNull(message = "nationalId is required")
    private String nationalId;
    @NotNull(message = "gender is required")
    private String gender;
    @NotNull(message = "nationality is required")
    private String nationality;
    @NotNull(message = "dateOfBirth is required")
    private String dateOfBirth;
    @NotNull(message = "phoneNo is required")
    private String phoneNo;
}
