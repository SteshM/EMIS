package com.emis.EMIS.wrappers.requestDTOs;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StreamStageDTO {
    private int streamId;
    private int learningStageId;
}
