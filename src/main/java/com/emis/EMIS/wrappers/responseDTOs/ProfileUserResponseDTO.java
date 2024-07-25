package com.emis.EMIS.wrappers.responseDTOs;

import lombok.*;

import java.util.Date;
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileUserResponseDTO {
       private int userId;

        private String firstName;

        private String middleName;

        private String lastName;

        private String email;

        private String nationalId;

        private String phoneNo;

        private String agencyName;

        private String firmName;

        private String emergencyContact;

        private String businessContact;

        private String businessEmail;

        private String businessPhone;

        private Date agreementStartDate;

        private Date agreementEndDate;

        private String adminRole;

        private String department;

        private String officePhone;

        private String tscNo;

        private String school;

        private String resource;

        private String employmentNo;

        private String officePhoneNo;

        private String registrationNo;

        private String gender;

        private String nationality;

        private String dateOfBirth;

       private String profile;

       private int yearsOfExperience;

    }


