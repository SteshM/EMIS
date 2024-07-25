package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class SchoolDTO {

    @NotNull(message = "SchoolName is required")
    private String schoolName;
    @Email
    @Column(unique = true)
    private String emailAddress;

    @NotBlank(message = "Mobile No cannot be blank")
    private String mobileNo;

    @NotNull(message = "postalAddress cannot be null")
    private String postalAddress;

    @NotNull(message = "postalCode cannot be null")
    private String postalCode;

    @NotNull(message = "moeRegistrationNo cannot be null")
    private String moeRegistrationNo;

    @NotNull(message = "categoryId cannot be null")
    private int categoryId;

    @NotNull(message = "schoolTypeId cannot be null")
    private int schoolTypeId;

    @NotNull(message = "resourceId cannot be null")
    private int resourceId;

    @NotNull(message = "schoolGenderId cannot be null")
    private int schoolGenderId;

    @NotNull(message = "curriculumId cannot be null")
    private int curriculumId;

    @NotNull(message = "County cannot be null")
    private String county;

    @NotNull(message = "subCounty cannot be null")
    private String subCounty;

    @NotNull(message = "Latitude is required")
    private Double latitude;

    @NotNull(message = "Longitude is required")
    private Double longitude;

    @NotNull(message = "logo is required")
    private String logo;

    private String schoolAdminEmail;

}
