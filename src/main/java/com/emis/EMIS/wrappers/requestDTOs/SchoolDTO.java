package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SchoolDTO {

    @NotNull(message = "SchoolName is required")
    private String schoolName;

    @Email
    @Column(unique = true)
    private String emailAddress;

    @NotBlank(message = "Mobile No cannot be blank")
    private String mobileNo;

    @NotNull(message = "This field cannot be null")
    private String postalAddress;

    @NotNull(message = "This field cannot be null")
    private String postalCode;

    @NotNull(message = "This field cannot be null")
    private String moeRegistrationNo;

    private int categoryId;
    private int schoolTypeId;
    private int schoolGenderId;
    private int curriculumId;
    private int subCountyId;
    @NotNull(message = "Latitude is required")
    private Double latitude;

    @NotNull(message = "Longitude is required")
    private Double longitude;
    private String diocese;
    private String logo;

}
