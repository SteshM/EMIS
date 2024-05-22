package com.emis.EMIS.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties("user.configs")

public class UserConfigs {
    private int successStatusCode;
    private int failedStatusCode;
    private String successStatusMessage;
}
