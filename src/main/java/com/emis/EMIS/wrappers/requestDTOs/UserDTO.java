package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.persistence.Column;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.springframework.data.jpa.repository.Temporal;

import java.sql.Date;

@Data
public class UserDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    @Email
    @Column(unique = true)
    private String email;
    private int nationalId;
    private int phoneNo;
    private String password;
    private int role;

}
