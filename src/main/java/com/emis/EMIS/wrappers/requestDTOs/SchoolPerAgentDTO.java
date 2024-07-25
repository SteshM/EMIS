package com.emis.EMIS.wrappers.requestDTOs;

import com.emis.EMIS.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class SchoolPerAgentDTO {
    private String id;
    private String name;
    private String type;
    private String category;
    private String schoolGender;
    private String curriculum;
    private String postalCode;
    private String postalAddress;
    private String moeRegistrationNumber;
    private String mobileNumber;
    private String emailAddress;
    private String subCounty;
    private String county;
    private List<Status> status;
    private String fromDate;
    private String toDate;
}
