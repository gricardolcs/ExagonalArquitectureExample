package com.jalasoft.bootcamp.applicant.infrastructure.dto.applicantstagequalification;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jalasoft.bootcamp.applicant.domain.applicantstagequalification.QualificationStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApplicantStageQualificationDTO
{
    @ApiModelProperty(
        example = "'-12345678909123'",
        dataType = "Long",
        required = true
    )
    @JsonProperty(value = "stageId")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long stageId;

    @ApiModelProperty(
        example = "Initial",
        dataType = "String",
        required = true
    )
    @JsonProperty(value = "stageName")
    private String stageName;

    @ApiModelProperty(
        allowableValues = "IN_PROGRESS, PASSED, FAILED",
        example = "IN_PROGRESS",
        required = true
    )
    @JsonProperty(value = "qualificationStatus")
    private QualificationStatus qualificationStatus;

    @ApiModelProperty(
        example = "'-12345678909222'",
        dataType = "Long",
        required = true
    )
    @JsonProperty(value = "applicantId")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long applicantId;

    @ApiModelProperty(
        example = "'-12345678567890'",
        dataType = "Long",
        required = true
    )
    @JsonProperty(value = "bootcampId")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long bootcampId;
}
