package com.jalasoft.bootcamp.bootcamp.infrastructure.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.QualificationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApplicantStageQualifiedEvent
{
    @JsonProperty("bootcampId")
    private final long bootcampId;

    @JsonProperty("stageId")
    private final long stageId;

    @JsonProperty("qualificationStatus")
    private final QualificationStatus qualificationStatus;

    @JsonProperty("applicantId")
    private final long applicantId;

    @JsonProperty("stageName")
    private final String stageName;
}
