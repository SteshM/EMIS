package com.emis.EMIS.wrappers.responseDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartnerDTO {
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
