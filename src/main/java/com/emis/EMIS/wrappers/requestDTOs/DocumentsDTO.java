package com.emis.EMIS.wrappers.requestDTOs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DocumentsDTO {
    private String id;
    private String schoolId;
    private Long menuCodeId;
    private Long documentTypeId;
    private String fileDocs;
}
