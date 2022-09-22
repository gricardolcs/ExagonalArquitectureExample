package com.jalasoft.bootcamp.applicant.infrastructure.dto.curriculum;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.Arrays;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class CurriculumDTO
{
    @ApiModelProperty(
        example = "curriculum.pdf"
    )
    @JsonProperty("fileName")
    private final String fileName;

    @ApiModelProperty(
        example = "Base64:==1asdSDewsd"
    )
    @JsonProperty("curriculumVitae")
    private final byte[] curriculumVitae;

    @ApiModelProperty(
        example = "content"
    )
    @JsonProperty("content")
    private final String content;
}
