package com.emis.EMIS.wrappers.requestDTOs;

import lombok.Data;

@Data
public class SchoolDTO {
    private String schoolName;
    private String schoolType;
    private String emailAddress;
    private String postalAddress;
    private String postalCode;
    private String contact;
    private String moeRegistrationNo;
    private String county;
    private String subCounty;
    private String location;
}
