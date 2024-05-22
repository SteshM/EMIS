package com.emis.EMIS.wrappers;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseDTO {
    private int statusCode;
    private String statusMessage;
    private Object result;
}
