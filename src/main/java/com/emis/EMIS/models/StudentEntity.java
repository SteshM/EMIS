package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
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
    @JoinColumn(name = "schoolId")
    private SchoolsEntity schools;


    @ManyToOne
    @JoinColumn(name = "guardianId")
    private GuardianEntity guardian;

    @OneToMany
    List<StudentMarksEntity>studentMarksEntityList;





    @Override
    public String toString() {
        return "StudentEntity{" +
                "studentId=" + studentId +
                ", registrationNo='" + registrationNo + '\'' +
                ", status=" + status +
                ", user=" + user +
                ", guardian=" + guardian +
                '}';
    }
}
