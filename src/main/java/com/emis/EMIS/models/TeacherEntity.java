package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "teachers")
public class TeacherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teacherId;
    private String tscNo;
    private int yearsOfExperience;
    private Status status;

    @OneToOne
    @JoinColumn(name = "schoolId")
    private SchoolsEntity school;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    @OneToMany
    List<SubjectEntity> subject;


}
