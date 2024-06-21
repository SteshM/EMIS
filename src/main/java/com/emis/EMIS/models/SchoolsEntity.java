package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Setter
@Getter
@Entity
@Table(name = "schools")
@SQLDelete(sql = "UPDATE schools set soft_delete=true where id=?")
@Where(clause = "soft_delete=false")
public class SchoolsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int schoolId;
    private String schoolName;
    private String schoolType;
    private String emailAddress;
    private String postalAddress;
    private String postalCode;
    private String contact;
    private String moeRegistrationNo;
    private String county;
    private String subCounty;
    private String location;
    private Status status;

    @Column(name = "softDelete", columnDefinition = "char(1) default 0")
    public boolean softDelete;

}
