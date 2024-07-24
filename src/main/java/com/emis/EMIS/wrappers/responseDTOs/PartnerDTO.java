package com.emis.EMIS.wrappers.responseDTOs;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartnerDTO {
    private int partnerId;
    private String resource;
    private int resourceId;
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
