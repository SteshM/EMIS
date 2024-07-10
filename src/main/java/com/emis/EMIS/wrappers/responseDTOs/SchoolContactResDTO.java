package com.emis.EMIS.wrappers.responseDTOs;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SchoolContactResDTO {
    private int schoolContactId;
    private String designation;
    private String emailAddress;
    private String name;
    private String phoneNumber;
    private String school;

}
