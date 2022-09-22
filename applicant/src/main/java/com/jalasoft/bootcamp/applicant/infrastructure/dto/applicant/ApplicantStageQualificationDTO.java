package com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jalasoft.bootcamp.applicant.domain.applicantstagequalification.ApplicantStageQualification;
import com.jalasoft.bootcamp.applicant.domain.applicantstagequalification.QualificationStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class ApplicantStageQualificationDTO {

    @ApiModelProperty(
        example = "'-4109313718736866412'"
    )
    @JsonProperty("stageId")
    @JsonSerialize(using = ToStringSerializer.class)
    private final long stageId;

    @ApiModelProperty(
        example = "Psychometric"
    )
    @JsonProperty("stageName")
    private final String stageName;

    @ApiModelProperty(
        allowableValues = "IN_PROGRESS, QUALIFIED_AND_READONLY, PASSED, FAILED, WITHDRAW",
        example = "IN_PROGRESS"
    )
    private final QualificationStatus status;

    @ApiModelProperty(
        example = "'-6641241093137187368'"
    )
    @JsonProperty("bootcampId")
    @JsonSerialize(using = ToStringSerializer.class)
    private final long bootcampId;

    public ApplicantStageQualificationDTO(
        final ApplicantStageQualification applicantStageQualification) {
        this.stageId = applicantStageQualification.getStageId();
        this.stageName = applicantStageQualification.getStageName().getName();
        this.status = applicantStageQualification.getQualificationStatus();
        this.bootcampId = applicantStageQualification.getBootcampId();
    }
}
