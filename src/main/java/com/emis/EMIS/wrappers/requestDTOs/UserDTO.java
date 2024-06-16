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

    @NotNull(message = "This field cannot be null")
    private String nationalId;

    @NotNull(message = "This field cannot be null")
    private String phoneNo;

    private String password;

    @NotNull(message = "This field cannot be null")
    private String agencyName;

    @NotNull(message = "This field cannot be null")
    private String firmName;

    @NotNull(message = "This field cannot be null")
    private String emergencyContact;

    @NotNull(message = "This field cannot be null")
    private String businessContact;

    @NotNull(message = "This field cannot be null")
    private String businessEmail;

    @NotNull(message = "This field cannot be null")
    private String businessPhone;

    @NotEmpty(message = "This field cannot be empty!")
    private Date agreementStartDate;

    @NotEmpty(message = "This field cannot be empty!")
    private Date agreementEndDate;

    @NotEmpty(message = "This field cannot be empty!")
    private String contractDetails;

    @NotEmpty(message = "This field cannot be empty!")
    private String adminRole;

    @NotEmpty(message = "This field cannot be empty!")
    private String department;

    @NotEmpty(message = "This field cannot be empty!")
    private String officePhone;

    @NotBlank(message = "This field cannot be blank!")
    private String tscNo;

    @NotBlank(message = "This field is mandatory!")
    private String employmentNo;

    @NotBlank(message = "This field is required!")
    private String officePhoneNo;

    @NotBlank(message = "This field is required!")
    private String registrationNo;

    @NotBlank(message = "This field is mandatory!")
    private String gender;

    @NotBlank(message = "This field is mandatory!")
    private String nationality;

    @NotBlank(message = "nationality is required!")
    private String dateOfBirth;

    @NotBlank(message = " date of birth is required!")
    private int profileId;

    @NotBlank(message = "profileId is required!")
    private String relationship;

    @NotBlank(message = " occupation is required!")
    private String occupation;




}
