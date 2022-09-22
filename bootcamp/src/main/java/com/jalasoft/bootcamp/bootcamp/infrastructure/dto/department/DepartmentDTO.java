package com.jalasoft.bootcamp.bootcamp.infrastructure.dto.department;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jalasoft.bootcamp.bootcamp.domain.department.Department;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class DepartmentDTO
{
    @ApiModelProperty(
        example = "-5319461058060181693"
    )
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    private long id;

    @ApiModelProperty(
        example = "Development"
    )
    @JsonProperty("name")
    private final String name;
    @ApiModelProperty(
        example = "This is the description of the department"
    )
    @JsonProperty("description")
    private final String description;

    public DepartmentDTO(Department department)
    {
        this.id = department.getId();
        this.name = department.getName();
        this.description = department.getDescription();
    }

    public DepartmentDTO(String name, String description)
    {
        this.name = name;
        this.description = description;
    }
}
