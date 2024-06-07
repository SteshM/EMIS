package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "students")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String registrationNo;
    private String gender;
    private String nationality;
    private String dateOfBirth;
    private Status status;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

}
