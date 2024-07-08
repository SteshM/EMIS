package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DocumentTypesDTO {
    @NotNull(message = "Document type name is required")
    private String name;
    @NotNull(message = "Menu code id is required")
    private String menuCodeId;
    @NotNull(message = "Remarks is required")
    private String remarks;
    @NotNull(message = "schoolId is required")
    private String schoolId;
    @NotNull(message = "this field is mandatory")
    private Boolean required;
}
