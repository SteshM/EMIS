package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SubmitSchoolDTO {
    @NotNull(message = "This field is required!")
    private int schoolId;

    @NotNull(message = "This field is required!")
    private String name;

    @NotBlank(message = "This field is required!")
    private  String remarks;

    @NotBlank(message = "This field is required!")
    private Boolean systemRole;

    @NotBlank(message = "This field is required!")
    private Long profileId;

    @NotBlank(message = "This field is required!")
    private List<Long> roleList;
}
