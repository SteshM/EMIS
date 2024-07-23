package com.emis.EMIS.wrappers.responseDTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ResourceResDTO {
    private int resourceId;
    private String resource;
}
