package com.jalasoft.bootcamp.bootcamp.infrastructure.dto.applicantworkflow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualification;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.QualificationStatus;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield.QualificationFieldSchema;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class ApplicantQualificationDTO
{
    @ApiModelProperty(
        example = "-5319461058060181633"
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
        example = "Passed successfully"
    )
    @JsonProperty("comment")
    private final String comment;

    @ApiModelProperty(
        example = "2021-06-07"
    )
    @JsonProperty("appliedDate")
    private final LocalDate appliedDate;

    @ApiModelProperty(
        example = "2021-12-07"
    )
    @JsonProperty("submitDate")
    private final LocalDateTime submitDate;

    @JsonProperty("applicantQualification")
    private final List<QualificationFieldSchema> qualificationFieldSchemas;

    @ApiModelProperty(
        allowableValues = "IN_PROGRESS, QUALIFIED_AND_READONLY, PASSED, FAILED, WITHDRAW",
        example = "IN_PROGRESS"
    )
    @JsonProperty("qualificationStatus")
    private final QualificationStatus qualificationStatus;

    @JsonProperty("isEnglishType")
    private boolean isEnglishType;

    @JsonCreator
    public ApplicantQualificationDTO(final ApplicantStageQualification applicantStageQualification)
    {
        this.stageId = applicantStageQualification.getStage().getId();
        this.stageName = applicantStageQualification.getStage().getStageName().getName();
        this.comment = applicantStageQualification.getComment().getComment();
        this.appliedDate = applicantStageQualification.getAppliedDate().getAppliedDate();
        this.submitDate = applicantStageQualification.getSubmitDate() != null
            ? applicantStageQualification.getSubmitDate().getSubmitDate() : null;
        this.qualificationFieldSchemas = applicantStageQualification.getQualificationFields()
            .stream()
            .map(qualificationField -> qualificationField.getQualificationFieldSchema())
            .collect(Collectors.toList());
        this.qualificationStatus = applicantStageQualification.getQualificationStatus();
        this.isEnglishType = applicantStageQualification.getStage().isEnglishType();
    }
}
