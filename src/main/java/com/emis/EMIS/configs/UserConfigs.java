package com.emis.EMIS.configs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data

@ConfigurationProperties("emis")
public class UserConfigs {
    private int successStatusCode;
    private int failedStatusCode;
    private String successStatusMessage;
    private int otpLength;
//    @Value("${email.url}")
//    private String url;
    private int otpExpiryDurationInMinutes;
    private  int invalidStatus;
    private String otpSubjectMessage;
    private String otpTextMessage;
    private String otpChangePasswordSubjectMessage;
    private String otpChangePasswordTextMessage;
    private String passwordResetSubjectMessage;
    private String passwordResetTextMessage;


}
