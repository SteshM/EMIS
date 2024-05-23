package com.emis.EMIS.wrappers;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ResponseDTO {
    private int statusCode;
    private String statusMessage;
    private Object result;
}
