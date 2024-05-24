package com.emis.EMIS.utils;

import com.emis.EMIS.configs.UserConfigs;
import com.emis.EMIS.wrappers.ResponseDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Data
@Component
@RequiredArgsConstructor
public class ResponseManager {
//
//    ResponseDTO responseDTO = new ResponseDTO();
//        responseDTO.setStatusCode(200);
//        responseDTO.setStatusDescription(message);
//        responseDTO.setData(data instanceof List ? (List<?>) data : List.of(data));
//        return responseDTO;
//}
//
//public static ResponseDTO createFailedResponse(int statusCode , String message){
//    ResponseDTO responseDTO = new ResponseDTO();
//    responseDTO.setStatusCode(statusCode);
//    responseDTO.setStatusDescription(message);
//    return responseDTO;
//}
private final UserConfigs userConfigs;
public ResponseDTO successResponse(String statusMessage,Object result){
    var response = new ResponseDTO();
    response.setStatusCode(userConfigs.getSuccessStatusCode());
    response.setStatusMessage(statusMessage);
    response.setResult(result);
    return response;
}
    public ResponseDTO failedResponse(String statusMessage,int statusCode){
        var response = new ResponseDTO();
        response.setStatusCode(statusCode);
        response.setStatusMessage(statusMessage);

        return response;
    }
}
