package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DocumentTypeCodesDTO {
    @NotNull(message = "Document type code is required")
    private String name;
    private String remarks;
    private boolean required;
    private Integer recordsRequired;
}
