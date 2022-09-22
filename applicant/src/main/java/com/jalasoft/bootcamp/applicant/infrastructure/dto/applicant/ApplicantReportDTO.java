package com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jalasoft.bootcamp.applicant.domain.applicant.Applicant;
import com.jalasoft.bootcamp.applicant.infrastructure.dto.report.ReportDTO;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Getter;

@Getter
public class ApplicantReportDTO {

    @ApiModelProperty(
        example = "'-7187368664014109313'"
    )
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    private final long id;

    private final List<ReportDTO> reports;

    public ApplicantReportDTO(Applicant applicant) {
        this.id = applicant.getId();
        this.reports = ReportDTO.convert(applicant.getReports());
    }
}
