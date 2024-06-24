package com.emis.EMIS.wrappers.requestDTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class SchoolContactsDTO {
    private String designation;
    private String designationId;
    private String emailAddress;
    private String name;
    private String phoneNumber;
    private String school;
    private String schoolId;
    private Date createdOn;
    private String createdBy;

}
