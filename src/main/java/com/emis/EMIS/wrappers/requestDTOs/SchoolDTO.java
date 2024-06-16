package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SchoolDTO {
    @NotNull(message = "SchoolName is required")
    private String schoolName;
    @NotBlank(message = "School type cannot be blank")
    private String schoolType;
    @Email
    @Column(unique = true)
    private String emailAddress;
    @NotNull(message = "This field cannot be null")
    private String postalAddress;
    @NotNull(message = "This field cannot be null")
    private String postalCode;
    @NotBlank(message = "This field cannot be blank")
    private String contact;
    @NotNull(message = "This field cannot be null")
    private String moeRegistrationNo;
    @NotBlank(message = "This field cannot be blank")
    private String county;
    @NotNull(message = "This field cannot be null")
    private String subCounty;
    @NotBlank(message = "This field cannot be blank")
    private String location;
}
