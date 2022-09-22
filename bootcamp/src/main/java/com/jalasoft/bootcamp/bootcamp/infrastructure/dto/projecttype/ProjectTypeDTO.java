package com.jalasoft.bootcamp.bootcamp.infrastructure.dto.projecttype;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jalasoft.bootcamp.bootcamp.domain.projecttype.ProjectType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class ProjectTypeDTO {

    @ApiModelProperty(
        example = "-4615319058060181693"
    )
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    private final long id;

    @ApiModelProperty(
        example = "Bootcamp"
    )
    @JsonProperty("name")
    private final String name;

    public ProjectTypeDTO(ProjectType projectType) {
        this.id = projectType.getId();
        this.name = projectType.getName().getName();
    }
}
