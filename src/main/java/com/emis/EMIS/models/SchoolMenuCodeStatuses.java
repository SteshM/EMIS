package com.emis.EMIS.models;

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
    @JoinColumn(name = "school_id")
    private SchoolsEntity schoolsEntity;

    @ManyToOne
    @JoinColumn(name = "menu_codes_id")
    private MenuCodes menuCodes;

    @Lob
    @Column(name = "remarks", columnDefinition = "TEXT", nullable = false)
    private String remarks;
    private String status;  //Pending,Rejected,Completed
    @Column(name = "remark_status")
    private String remarkStatus ;
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
