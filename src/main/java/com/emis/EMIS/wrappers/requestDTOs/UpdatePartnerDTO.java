package com.emis.EMIS.wrappers.requestDTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class UpdatePartnerDTO {
    private String resource;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String nationalId;
    private String phoneNo;
    private String firmName;
    private String emergencyContact;
    private String businessContact;
    private String businessEmail;
    private Date agreementStartDate;
    private Date agreementEndDate;
}
