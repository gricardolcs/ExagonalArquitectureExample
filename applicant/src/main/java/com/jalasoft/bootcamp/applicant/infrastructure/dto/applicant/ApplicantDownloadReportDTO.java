package com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApplicantDownloadReportDTO {

    @ApiModelProperty(
        example = "'-7187368664014109313'"
    )
    @JsonProperty("bootcampId")
    @JsonSerialize(using = ToStringSerializer.class)
    private final long bootcampId;

    @ApiModelProperty(
        example = "Report.txt"
    )
    @JsonProperty("fileName")
    private final String fileName;

    @ApiModelProperty(
        example = "[20,32,12]"
    )
    @JsonProperty("report")
    private final byte[] report;
}
