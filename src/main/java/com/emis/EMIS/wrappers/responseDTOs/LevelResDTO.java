package com.emis.EMIS.wrappers.responseDTOs;

import com.emis.EMIS.models.LevelsEntity;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Setter
@Getter
public class LevelResDTO {
    private int levelId;
    private int curriculumId;
    private String levelName;
}
