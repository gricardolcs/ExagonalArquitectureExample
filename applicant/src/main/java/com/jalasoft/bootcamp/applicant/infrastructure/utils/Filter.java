package com.jalasoft.bootcamp.applicant.infrastructure.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class Filter
{
    @ApiModelProperty(
        example ="skill"
    )
    @JsonProperty("attribute")
    @JsonInclude(Include.NON_NULL)
    private final String attribute;

    @ApiModelProperty(
        example = "[java, python]"
    )
    @JsonProperty("values")
    @JsonInclude(Include.NON_NULL)
    private final List<String> values;
}
