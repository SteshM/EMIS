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
    private String email;
    private int nationalId;
    private int contactNo;
    private int password;
    @Column(nullable = false, updatable = false)
    private Date dateCreated;
    private String createdBy;
    @Column(nullable = false)
    private Date dateModified;
    private String modifiedBy;
    private String status;

}
