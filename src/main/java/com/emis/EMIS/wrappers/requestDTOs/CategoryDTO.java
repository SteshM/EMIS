package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryDTO {
//    private int schoolId;
    @NotBlank(message = "This field is required!")
    private String category;
}
