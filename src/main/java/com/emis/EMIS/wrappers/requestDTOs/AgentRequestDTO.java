package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AgentRequestDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    @Email
    @Column(unique = true)
    private String email;
    private String nationalId;
    private String phoneNo;
    private String password;
    private String roles;
    private String username;
    private String agencyName;
    private String jobTitle;
    private String emergencyContact;
}
