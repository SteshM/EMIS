package com.emis.EMIS.exceptionhandler;

import com.emis.EMIS.wrappers.responseDTOs.ResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                             HttpHeaders headers,
                                                             HttpStatusCode status,
                                                             WebRequest request) {
        List<String> errorList = new ArrayList<>();
        for(ObjectError objectError : ex.getBindingResult().getAllErrors()){
            String defaultMessage = objectError.getDefaultMessage();
            errorList.add(defaultMessage);
        }

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatusCode(402);
        responseDTO.setStatusMessage("Failed Validation");
        responseDTO.setResult(errorList);
        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
    }
}
