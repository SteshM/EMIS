package com.emis.EMIS.wrappers;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AgentDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String nationalId;
    private String phoneNo;
    private  String password;
    private String agencyName;
    private String agentType;
    private String emergencyContact;
}
