package com.emis.EMIS.exception;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.NoSuchElementException;
@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler extends Throwable {

        ProblemDetail errorDetails;
        @ExceptionHandler(Exception.class)
        public ProblemDetail errorHandler (Exception ex){
            log.error("encode exception",ex);
            if(ex instanceof BadCredentialsException){
                errorDetails = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
                errorDetails.setProperty("Reason","Authentication Failure");
            }
            if(ex instanceof AccessDeniedException){
                errorDetails = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
                errorDetails.setProperty("Reason","Not Authorized");
            }
            if(ex instanceof NoSuchElementException){
                errorDetails = ProblemDetail
                        .forStatusAndDetail(HttpStatusCode.valueOf(409), ex.getMessage());
                errorDetails.setProperty("Reason", "Some fields are missing");
            }
            if(ex instanceof NoSuchElementException){
                errorDetails = ProblemDetail
                        .forStatusAndDetail(HttpStatusCode.valueOf(409), ex.getMessage());
                errorDetails.setProperty("Reason", "One field or more are missing");
            }
            if(ex instanceof MethodArgumentNotValidException){
                errorDetails = ProblemDetail
                        .forStatusAndDetail(HttpStatusCode.valueOf(400), ex.getMessage());
                errorDetails.setProperty("Reason", "Fields validation failed. Check to see if the request is ok");
            }
            if(ex instanceof ChangeSetPersister.NotFoundException){
                errorDetails = ProblemDetail
                        .forStatusAndDetail(HttpStatusCode.valueOf(404), ex.getMessage());
                errorDetails.setProperty("Reason", "Not found");
            }
            if(ex instanceof UsernameNotFoundException){
                errorDetails = ProblemDetail
                        .forStatusAndDetail(HttpStatusCode.valueOf(404), ex.getMessage());
                errorDetails.setProperty("Reason", "You are logged out");}
            if(ex instanceof ExpiredJwtException){
                errorDetails = ProblemDetail
                        .forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
                errorDetails.setProperty("Reason", "Expired JWT Token");
            }

            if(ex instanceof NullPointerException){
                errorDetails = ProblemDetail
                        .forStatusAndDetail(HttpStatusCode.valueOf(500), ex.getMessage());
                errorDetails.setProperty("Reason", "Null Pointer Exception");
            }
            return  errorDetails;
        }


}
