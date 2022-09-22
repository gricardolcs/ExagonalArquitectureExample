package com.jalasoft.bootcamp.bootcamp.infrastructure.dto.bootcamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BootcampApplicantAssociationDTO
{
    @ApiModelProperty(
        example = "-34",
        required = true
    )
    @JsonProperty("bootcampId")
    private final long bootcampId;

    @ApiModelProperty(
        example = "[-1, -2, -3]",
        dataType = "Set",
        required = true
    )
    @JsonProperty("applicantIds")
    private final Set<Long> applicantIds;
}
