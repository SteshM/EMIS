package com.emis.EMIS.wrappers.responseDTOs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DocumentTypeResDTO {
    private int menuCodeId;
    private String name;
    private String remarks;
    private boolean required;
    private int recordsRequired;
}
