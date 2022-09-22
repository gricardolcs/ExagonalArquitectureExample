package com.jalasoft.bootcamp.bootcamp.infrastructure.dto.applicantstagequalification;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.QualificationStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApplicantStageQualificationDTO {

    @ApiModelProperty(
        example = "-839982746646388388"
    )
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonProperty("applicantId")
    private final long applicantId;

    @ApiModelProperty(
        example = "-4683998276463883881"
    )
    @JsonProperty("stageId")
    @JsonSerialize(using = ToStringSerializer.class)
    private final long stageId;

    @ApiModelProperty(
        allowableValues = "IN_PROGRESS, QUALIFIED_AND_READONLY, PASSED, FAILED, WITHDRAW",
        example = "IN_PROGRESS"
    )
    @JsonProperty("qualificationStatus")
    private final QualificationStatus qualificationStatus;
}
