package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.Data;


@Data
public class UserDTO {
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



}
