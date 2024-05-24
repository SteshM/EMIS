package com.emis.EMIS.configs;

import lombok.Data;
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
    @Value("${email.url}")
    private String url;
}
