package com.emis.EMIS.models;

import com.emis.EMIS.enums.RemarksClarificationStatus;
import com.emis.EMIS.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "SchoolMenuCodeStatuses")
public class SchoolMenuCodeStatuses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int schoolMenuCodeStatusId;
    @ManyToOne
    @JoinColumn(name = "schoolId")
    private SchoolsEntity schoolsEntity;

    @ManyToOne
    @JoinColumn(name = "menuCodeId")
    private MenuCodes menuCodes;

    @Lob
    @Column(name = "remarks", columnDefinition = "TEXT", nullable = false)
    private String remarks;
    private Status status;  //Pending,Rejected,Completed
    @Column(name = "remark_status")
    private RemarksClarificationStatus remarkStatus ;
//            = RemarksClarificationStatus.CLOSED.name();  //OPEN,REVIEW,CLOSED
    private Integer completionPercentage;  //Pending,Rejected,Completed

    private int createdBy;
    private int approvedBy;
    private int rejectedBy;
    private int submittedBy;
    private int clarificationRaisedBy;
    private int clarificationRepliedBy;
    private int clarificationClosedBy;

}
