package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DirectorsDTO {
    @NotNull(message = "directorId cannot be null")
    private int directorId;
    @NotNull(message = "name cannot be null")
    private String name;
    @NotNull(message = "identityTypeId cannot be null")
    private int identityTypeId;
    @NotNull(message = "identityNumber cannot be null")
    private int identityNumber;
    @NotNull(message = "pin cannot be null")
    private String pin;
    @NotNull(message = "identityDoc cannot be null")
    private String identityDoc;
    @NotNull(message = "pinCertificateDoc cannot be null")
    private String pinCertificateDoc;
    @NotNull(message = "menuCodeId cannot be null")
    private int menuCodeId;
    @NotNull(message = " documentTypeId cannot be null")
    private int documentTypeId;
    @NotNull(message = "schoolId cannot be null")
    private int schoolId;


}
