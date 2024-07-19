package com.emis.EMIS.wrappers.responseDTOs;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentTypeResDTO {
    private int menuCodeId;
    private String name;
    private String remarks;
    private boolean required;
    private int recordsRequired;
}
