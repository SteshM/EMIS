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
    private int otpExpiryDurationInMinutes;
    private  int invalidStatus;
    private int activeStatus;
    private int inactiveStatus;
    private int partnerPendingStatus;
    private int deletedStatus;


    private String CANCREATEADMIN;
    private String CANENROLAGENT;
    private String CANUPDATELAGENT;
    private String CANVIEWAGENTS;
    private String CANDELETEAGENTS;
}
