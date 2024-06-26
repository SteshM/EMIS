package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DialectOverride;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;

import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "otp")
@SQLRestriction("status = '1'")
public class OTPEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int otpId;
    private String otp;
    private Date dateCreated = new Date();
    private Status status;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity userEntity;

}
