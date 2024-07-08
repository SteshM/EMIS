package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.internal.bytebuddy.implementation.bind.MethodDelegationBinder;

import java.util.Date;

@Setter
@Getter
public class SchoolContactsDTO {
    @NotNull(message = "designation is required!")
    private String designation;
    @NotNull(message = "designationId is required!")
    private String designationId;
    @NotNull(message = "emailAddress is required!")
    @Email
    @Column(unique = true)
    private String emailAddress;
    @NotNull(message = "name is required!")
    private String name;
    @NotNull(message = "phoneNumber is required!")
    private String phoneNumber;
    @NotNull(message = "school is required!")
    private String school;
    @NotNull(message = "schoolId is required!")
    private String schoolId;
    @NotNull(message = "date created  is required!")
    private Date createdOn;
    @NotNull(message = "createdBy is required!")
    private String createdBy;

}
