package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "partnerApproval")
public class PartnerApprovalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int partnerApprovalId;

    private String remarks;
    private Status status; //Approved,Submitted,Rejected,Clarification,Pending
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated ;
    private String createdBy;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    private String modifiedBy;

    @ManyToOne
    @JoinColumn(name = "partnerId")
    private PartnerInfoEntity partnerInfo;

    @ManyToOne
    @JoinColumn(name = "schoolId")
    private SchoolsEntity schoolsEntity;


}
