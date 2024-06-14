package com.emis.EMIS.wrappers.requestDTOs;

import lombok.Data;

@Data
public class PageRequestDTO {
    private int pageNo;
    private int pageSize;
    private String orderBy;
    private String direction;
}
