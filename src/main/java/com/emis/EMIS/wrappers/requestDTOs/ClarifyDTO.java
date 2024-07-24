package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClarifyDTO {
    @NotNull(message = "schoolId cannot be null")
    private int schoolId;
    @NotNull(message = "menuCodeId cannot be null")
    private int menuCodeId;
    @NotNull(message = "remarks cannot be null")
    private String remarks;
}
