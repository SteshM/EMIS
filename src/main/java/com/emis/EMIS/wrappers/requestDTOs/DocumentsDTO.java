package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DocumentsDTO {
//    @NotNull(message = "Id is required")
//    private int id;
    @NotNull(message = "schoolId is required")
    private int schoolId;
    @NotNull(message = "menuCodeId is required")
    private int menuCodeId;
//    @NotNull(message = "supportDocId is required")
//    private int supportDocId;
    @NotNull(message = "documentTypeId is required")
    private int documentTypeId;
    @NotNull(message = "schoolMenuCodeStatusId is required")
    private int schoolMenuCodeStatusId;

//    @NotNull(message = "fileDocs is required")
//    private String fileDocs;
}
