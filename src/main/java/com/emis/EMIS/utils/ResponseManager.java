package com.emis.EMIS.utils;

import com.emis.EMIS.configs.UserConfigs;
import com.emis.EMIS.wrappers.ResponseDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Data
@Component
@RequiredArgsConstructor
public class ResponseManager {
private final UserConfigs userConfigs;
public ResponseDTO successResponse(Object result){
    var response = new ResponseDTO();
    response.setStatusCode(userConfigs.getSuccessStatusCode());
    response.setStatusMessage(userConfigs.getSuccessStatusMessage());
    response.setResult(result);
    return response;
}
    public ResponseDTO failedResponse(String statusMessage,Object result){
        var response = new ResponseDTO();
        response.setStatusCode(userConfigs.getFailedStatusCode());
        response.setStatusMessage(statusMessage);
        response.setResult(result);
        return response;
    }
}
