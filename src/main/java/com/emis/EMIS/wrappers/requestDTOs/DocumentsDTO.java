package com.emis.EMIS.wrappers.requestDTOs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DocumentsDTO {
    private int id;
    private int schoolId;
    private int menuCodeId;
    private int supportDocId;
    private int documentTypeId;
    private String fileDocs;
}
