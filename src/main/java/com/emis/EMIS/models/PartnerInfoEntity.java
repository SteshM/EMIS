package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "partner_info")
@SQLDelete(sql = "UPDATE partner_info set soft_delete=true where id=?")
@Where(clause = "soft_delete=false")
public class PartnerInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int partnerId;
    private String firmName;
    private String emergencyContact;
    private String businessContact;
    private String businessEmail;
    private Date agreementStartDate;
    private Date agreementEndDate;
    private String contractDetails;
    private Status status;

    @Column(name = "softDelete", columnDefinition = "char(1) default 0")
    public boolean softDelete;

    @ManyToOne
    @JoinColumn(name = "userId")
    UserEntity userEntity;

}
