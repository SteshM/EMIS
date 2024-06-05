package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
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
    private String businessContact;
    private String businessEmail;
    private Date agreementStartDate;
    private Date agreementEndDate;
    private String contractDetails;
    private Status status;
    @ManyToOne
    @JoinColumn(name = "userId")
    UserEntity userEntity;

}
