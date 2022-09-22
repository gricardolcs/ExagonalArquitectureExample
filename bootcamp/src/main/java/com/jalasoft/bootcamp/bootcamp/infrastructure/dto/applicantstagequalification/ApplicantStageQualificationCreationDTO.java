package com.jalasoft.bootcamp.bootcamp.infrastructure.dto.applicantstagequalification;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.QualificationStatus;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield.QualificationFieldSchema;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApplicantStageQualificationCreationDTO
{
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
        example = "2022-02-04T15:38:23.000Z"
    )
    @JsonProperty("submitDate")
    private final LocalDateTime submitDate;

    @ApiModelProperty(
        example = "[ "
            + "{ label: 'Score:', qualification: 87, type: NUMERIC_INPUT },"
            + "{ label: 'Upload Report:', fileName: File.txt, type: UPLOAD_FILE_INPUT },"
            + "{ label: 'Recommended:', elements: [ YES, NO ], selectedElement: 'YES', type: "
            + "DROPDOWN_INPUT }"
            + "]"
    )
    @JsonProperty("qualificationFieldSchemas")
    private final List<QualificationFieldSchema> schema;

    @ApiModelProperty(
        allowableValues = "IN_PROGRESS, QUALIFIED_AND_READONLY, PASSED, FAILED, WITHDRAW",
        example = "IN_PROGRESS"
    )
    @JsonProperty("qualificationStatus")
    private final QualificationStatus qualificationStatus;

    @ApiModelProperty(
        example = "2"
    )
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonProperty("nextStageId")
    private final long nextStageId;
}
