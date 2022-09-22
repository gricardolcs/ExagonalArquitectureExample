package com.jalasoft.bootcamp.applicant.infrastructure.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jalasoft.bootcamp.applicant.domain.applicantstagequalification.QualificationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ApplicantStageQualifiedEvent
{
    @JsonProperty("stageId")
    private Long stageId;

    @JsonProperty("stageName")
    private String stageName;

    @JsonProperty("qualificationStatus")
    private QualificationStatus qualificationStatus;

    @JsonProperty("applicantId")
    private Long applicantId;

    @JsonProperty("bootcampId")
    private Long bootcampId;
}
