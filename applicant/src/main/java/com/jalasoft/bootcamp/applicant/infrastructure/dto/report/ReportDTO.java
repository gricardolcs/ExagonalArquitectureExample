package com.jalasoft.bootcamp.applicant.infrastructure.dto.report;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jalasoft.bootcamp.applicant.domain.applicant.Report;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class ReportDTO {

    @ApiModelProperty(
        example = "'-7187368664014109313'"
    )
    @JsonProperty("bootcampId")
    private final long bootcampId;

    @ApiModelProperty(
        example = "Report.txt"
    )
    @JsonProperty("fileName")
    private final String fileName;

    @ApiModelProperty(
        example = "[12,3,1,24,34,23,65]"
    )
    @JsonProperty("report")
    private byte[] report;

    public ReportDTO(Report report) {
        this.bootcampId = report.getBootcampId();
        this.fileName = report.getFileName();
        this.report = report.getReport();
    }

    public static List<ReportDTO> convert(List<Report> reports) {
        return reports.stream().map(ReportDTO::new).collect(Collectors.toList());
    }
}
