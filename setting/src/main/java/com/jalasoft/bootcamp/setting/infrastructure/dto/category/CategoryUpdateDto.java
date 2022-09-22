package com.jalasoft.bootcamp.setting.infrastructure.dto.category;

import com.jalasoft.bootcamp.setting.infrastructure.dto.skill.SkillRecord;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUpdateDto
{
    @ApiModelProperty(
        example = "-5319461058060181693"
    )
    private long id;

    @ApiModelProperty(
        example = "category example"
    )
    private String name;

    @ApiModelProperty(
        example = "[{'oldSkill': 'java', 'newSkill': 'java edit' }]"
    )
    private List<SkillRecord> records;
}
