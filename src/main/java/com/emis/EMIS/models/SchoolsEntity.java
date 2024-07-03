package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "schools")
public class SchoolsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int schoolId;
    private String schoolName;
    private String moeRegistrationNo;
    private String MobileNo;
    private String emailAddress;
    private String postalAddress;
    private String postalCode;
    private Status status;
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated = new Date() ;
    private String createdBy;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    private String modifiedBy;


    @ManyToOne
    @JoinColumn(name = "schoolTypeId")
    private SchoolType schoolType;

    @ManyToOne
    @JoinColumn(name = "schoolGenderId")
    private SchoolGender schoolGender;

    @ManyToOne
    @JoinColumn(name = "curriculumId")
    private CurriculumEntity curriculum;

    @ManyToOne
    @JoinColumn(name = "countyId")
    private CountyEntity countyEntity;

    @ManyToOne
    @JoinColumn(name = "subCountyId")
    private SubCountyEntity subCountyEntity;

    @ManyToOne
    @JoinColumn(name = "countyDioceseId")
    private CountyDioceseEntity countyDioceseEntity;

    @OneToMany()
    List<StreamsEntity>streamsEntityList;

    @ManyToOne
    @JoinColumn(name = "dioceseId")
    private DioceseEntity dioceseEntity;
    private String logoName;
    private String logoUrl;
    private String logoSize;
    private String logoType;
    private String logoKey;
    private String location;
    private String road;
    private String state;
//    private double latitude;
//    private double longitude;
//    private int approvedBy = 0;
//    private int rejectedBy = 0;
//    private int submittedBy = 0;
//    private int clarificationRaisedBy = 0;
//    private int clarificationRepliedBy = 0;
//    private int clarificationClosedBy = 0;

}
