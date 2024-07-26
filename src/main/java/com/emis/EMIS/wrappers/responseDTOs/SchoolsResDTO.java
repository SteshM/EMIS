package com.emis.EMIS.wrappers.responseDTOs;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SchoolsResDTO {
    private int schoolId;
    private String schoolName;
    private String schoolGender;
    private String curriculum;
    private String resource;
    private String schoolType;
    private String category;
    private String emailAddress;
    private String mobileNo;
    private String postalAddress;
    private String postalCode;
    private String moeRegistrationNo;
    private int categoryId;
    private int resourceId;
    private int schoolTypeId;
    private int schoolGenderId;
    private int curriculumId;
    private String county;
    private String  subCounty;
    private String logo;
    private Double longitude;
    private Double latitude;
    private String schoolAdminEmail;













}
