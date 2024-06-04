package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class SchoolDTO {
    private String schoolName;
    private String schoolType;
    @Email
    @Column(unique = true)
    private String emailAddress;
    private String postalAddress;
    private String postalCode;
    private String contact;
    private String moeRegistrationNo;
    private String county;
    private String subCounty;
    private String location;
}
