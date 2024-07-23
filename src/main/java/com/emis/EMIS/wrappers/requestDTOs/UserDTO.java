package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;


@Data
public class UserDTO {
    @NotBlank(message = "First name is required!")
    private String firstName;

    @NotBlank(message = "Middle name is required!")
    private String middleName;

    @NotBlank(message = "Last name is required!")
    private String lastName;

    @Email
    @Column(unique = true)
    @NotBlank(message = " email is required!")
    private String email;

    @NotNull(message = "This field cannot be null!")
    private String nationalId;

    @NotNull(message = "This field cannot be null!")
    private String phoneNo;

    private String password;

    @NotNull(message = "This field cannot be null!")
    private String agencyName;

    @NotNull(message = "This field cannot be null!")
    private String firmName;

    @NotNull(message = "This field cannot be null")
    private String emergencyContact;

    @NotNull(message = "This field cannot be null")
    private String businessContact;

    @NotNull(message = "This field cannot be null")
    private String businessEmail;

    @NotNull(message = "This field cannot be null")
    private String businessPhone;

    @NotNull(message = "agreement end date cannot be null!")
    private Date agreementStartDate;

    @NotNull(message = "agreement date cannot be null!")
    private Date agreementEndDate;

    @NotEmpty(message = "admin role cannot be empty!")
    private String adminRole;

    @NotEmpty(message = "department cannot be empty!")
    private String department;

    @NotEmpty(message = "Office Phone cannot be empty!")
    private String officePhone;

    @NotBlank(message = "tsc no cannot be blank!")
    private String tscNo;

    @NotBlank(message = "schoolId cannot be blank!")
    private int schoolId;

    @NotBlank(message = "resourceId cannot be blank!")
    private int resourceId;

    @NotBlank(message = "Employment no is mandatory!")
    private String employmentNo;

    @NotBlank(message = "officePhoneNo is required!")
    private String officePhoneNo;

    @NotBlank(message = "registrationNo is required!")
    private String registrationNo;

    @NotBlank(message = "gender is mandatory!")
    private String gender;

    @NotBlank(message = "Nationality is mandatory!")
    private String nationality;

    @NotBlank(message = "date of birth is required!")
    private String dateOfBirth;

    @NotNull(message = " profile id  is required!")
    private int profileId;

    @NotBlank(message = "Relationship is required!")
    private String relationship;

    @NotBlank(message = " occupation is required!")
    private String occupation;




}
