package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity
@Table(name = "students")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;
     private String registrationNo;
    private Status status;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;


    @ManyToOne
    @JoinColumn(name = "guardianId")
    private GuardianEntity guardian;


}
