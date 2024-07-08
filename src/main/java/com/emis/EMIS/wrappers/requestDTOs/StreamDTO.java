package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StreamDTO {
    @NotNull(message = "schoolId cannot be null")
    private int schoolId;

    @NotNull(message = "stream cannot be null")
    private String stream;
}
