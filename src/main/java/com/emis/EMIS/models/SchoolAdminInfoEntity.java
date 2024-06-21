package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * Created By Stella
 * created on 01/06/2024
 */

@Setter
@Getter
@Entity
@Table(name = "school_admin_info")
@SQLDelete(sql = "UPDATE agent_info set soft_delete=true where id=?")
@Where(clause = "soft_delete=false")
public class SchoolAdminInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int schoolAdminId;
    private String adminRole;
    private String department;
    private String officePhone;
    private String tscNumber;
    private Status status;

    @Column(name = "softDelete", columnDefinition = "char(1) default 0")
    public boolean softDelete;

    @OneToOne
    @JoinColumn(name = "userId")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "schoolId")
    private SchoolsEntity schools;
}
