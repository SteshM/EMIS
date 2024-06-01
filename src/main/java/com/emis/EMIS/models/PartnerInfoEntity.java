package com.emis.EMIS.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "partnerInfo")
public class PartnerInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int partnerInfoId;
    private String firmName;
    private String emergencyContact;
    private String partnerType;
    private String businessContact;
    private String businessEmail;
    private String businessPhone;
    private Date agreementStartDate;
    private Date agreementEndDate;
    private String contractDetails;

    @ManyToOne
    @JoinColumn(name = "userId")
    UserEntity userEntity;

}
