package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity
@Table(name = "students")
@SQLDelete(sql = "UPDATE students set soft_delete=true where id=?")
@Where(clause = "soft_delete=false")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;
     private String registrationNo;
    private Status status;

    @Column(name = "softDelete", columnDefinition = "char(1) default 0")
    public boolean softDelete;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;


    @ManyToOne
    @JoinColumn(name = "guardianId")
    private GuardianEntity guardian;


}
