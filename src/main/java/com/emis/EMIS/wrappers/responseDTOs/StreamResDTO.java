package com.emis.EMIS.wrappers.responseDTOs;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StreamResDTO {
    private int streamId;
    private String stream;
}
