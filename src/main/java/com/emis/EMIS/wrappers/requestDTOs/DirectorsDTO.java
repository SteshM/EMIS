package com.emis.EMIS.wrappers.requestDTOs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DirectorsDTO {
    private int directorId;
    private String name;
    private int identityTypeId;
    private int identityNumber;

    private String pin;

    private String identityDoc;
    private String pinCertificateDoc;

    private int menuCodeId;
    private int documentTypeId;
    private int schoolId;


}
