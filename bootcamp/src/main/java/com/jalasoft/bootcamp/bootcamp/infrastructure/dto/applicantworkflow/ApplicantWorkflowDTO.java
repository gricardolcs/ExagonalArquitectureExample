package com.jalasoft.bootcamp.bootcamp.infrastructure.dto.applicantworkflow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualification;
import com.jalasoft.bootcamp.bootcamp.domain.applicantworkflow.ApplicantWorkflow;
import io.swagger.annotations.ApiModelProperty;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class ApplicantWorkflowDTO
{
    @ApiModelProperty(
        example = "-6335319461058060181"
    )
    @JsonProperty("applicantId")
    @JsonSerialize(using = ToStringSerializer.class)
    private final long applicantId;

    @ApiModelProperty(
        example = "-9633531946105806018"
    )
    @JsonProperty("bootcampId")
    @JsonSerialize(using = ToStringSerializer.class)
    private final long bootcampId;

    @ApiModelProperty(
        example = ""
            + "[\n"
            + "        {\n"
            + "            \"stageId\": 2,\n"
            + "            \"stageName\": \"Psychometric\",\n"
            + "            \"comment\": \"COMMENT QUALIFICATION\",\n"
            + "            \"applicantQualification\": {\n"
            + "                \"label\": \"Result\",\n"
            + "                \"qualification\": \"1\",\n"
            + "                \"type\": \"SINGLE_INPUT\"\n"
            + "            },\n"
            + "            \"qualificationStatus\": \"PASSED\"\n"
            + "        },\n"
            + "    ]"
    )
    @JsonProperty("applicantQualifications")
    private final List<ApplicantQualificationDTO> applicantQualifications;

    @JsonCreator
    public ApplicantWorkflowDTO(final ApplicantWorkflow applicantWorkflow)
    {
        this.applicantId = applicantWorkflow.getApplicantId();
        this.bootcampId = applicantWorkflow.getBootcampId();
        this.applicantQualifications = getApplicantQualificationsDTO(
            applicantWorkflow.getApplicantStageQualificationList());
    }

    private List<ApplicantQualificationDTO> getApplicantQualificationsDTO(
        Set<ApplicantStageQualification> applicantStageQualificationList)
    {
        return applicantStageQualificationList.stream()
            .sorted(Comparator.comparing(applicantStageQualification -> applicantStageQualification.getStage()
                .getStageOrder().getOrder()))
            .map(ApplicantQualificationDTO::new)
            .collect(Collectors.toList());
    }

    public static List<ApplicantWorkflowDTO> convert(List<ApplicantWorkflow> applicantWorkflows)
    {
        return applicantWorkflows.stream().map(ApplicantWorkflowDTO::new)
            .collect(Collectors.toList());
    }
}
