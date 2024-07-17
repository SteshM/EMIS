package com.emis.EMIS.wrappers.requestDTOs;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClarifyDTO {
    private int schoolId;
    private Long menuCodeId;
    private String remarks;
}
