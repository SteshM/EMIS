package com.emis.EMIS.wrappers.requestDTOs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReplyOnClarificationDTO {
    private int schoolId;
    private int menuCodeId;
    private String remarks;
}
