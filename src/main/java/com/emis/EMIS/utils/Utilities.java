package com.emis.EMIS.utils;

import com.emis.EMIS.configs.UserConfigs;
import com.emis.EMIS.wrappers.ResponseDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.util.Base64;

@Data
@Component
@RequiredArgsConstructor
public class Utilities {
    private final UserConfigs userConfigs;

    public ResponseDTO successResponse(String statusMessage, Object result) {
        var response = new ResponseDTO();
        response.setStatusCode(userConfigs.getSuccessStatusCode());
        response.setStatusMessage(statusMessage);
        response.setResult(result);
        return response;
    }

    public ResponseDTO failedResponse(int statusCode, String statusMessage, Object result) {
        var response = new ResponseDTO();
        response.setStatusCode(statusCode);
        response.setStatusMessage(statusMessage);
        response.setResult(result);

        return response;
    }

    public String encoder(String text){
        return Base64.getEncoder().encodeToString(text.getBytes());
    }
}
