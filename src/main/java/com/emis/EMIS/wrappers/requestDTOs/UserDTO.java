package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.Date;


@Data
public class UserDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    @Email
    @Column(unique = true)
    private String email;
    private String nationalId;
    private String phoneNo;
    private String password;
    private String roles;
    private String agencyName;
    private String agentType;
    private String jobTitle;
    private String firmName;
    private String emergencyContact;
    private String partnerType;
    private String businessContact;
    private String businessEmail;
    private String businessPhone;
    private Date agreementStartDate;
    private Date agreementEndDate;
    private String contractDetails;


}
