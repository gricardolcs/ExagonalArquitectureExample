package com.jalasoft.bootcamp.setting.infrastructure.dto.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jalasoft.bootcamp.setting.domain.category.Category;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CategoryDTO
{
    @ApiModelProperty(
        example = "-5319461058060181693"
    )
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonProperty("id")
    private Long id;

    @ApiModelProperty(
        example = "Programing Language"
    )
    @JsonProperty("name")
    @NotEmpty
    private final String name;

    @ApiModelProperty(
        example = "This is a description"
    )
    @JsonProperty("description")
    private final String description;

    @ApiModelProperty(
        example = "['C#', 'Java']"
    )
    @JsonProperty("skills")
    @NotNull
    private List<String> skills;

    public CategoryDTO(final Category category) {
        id = category.getId();
        name = category.getName();
        description = category.getDescription();
        skills = new ArrayList<>(category.getSkills());
    }

    @JsonCreator
    public CategoryDTO(final String name, final String description, final List<String> skills)
    {
        this.name = name;
        this.description = description;
        this.skills = skills;
    }

    public CategoryDTO setSkills(final List<String> skills)
    {
        this.skills = skills;
        return this;
    }
}
