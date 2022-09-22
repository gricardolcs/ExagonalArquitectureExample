package com.jalasoft.bootcamp.applicant.infrastructure.controller.applicant;

import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantDownloadReportDTO;
import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.fetch.ApplicantReportFetchUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan(basePackages="com.jalasoft.bootcamp.becommon.domain.exceptions")
@RestController
public class ApplicantDownloadReportController
{
    private final ApplicantReportFetchUseCase applicantReportFetchUseCase;

    @Autowired
    public ApplicantDownloadReportController(ApplicantReportFetchUseCase applicantReportFetchUseCase)
    {
        this.applicantReportFetchUseCase = applicantReportFetchUseCase;
    }

    @ApiOperation(
        value = "Get an applicant report",
        response = ApplicantDownloadReportDTO.class,
        notes = "Get a report of a Applicant given his id and the id of the bootcamp returns the " +
            "id of the bootcamp and the Applicant's report"
    )
    @ApiResponses( {
        @ApiResponse(
            code = 200,
            message = "Successful applicant's report obtained"
        ),
        @ApiResponse(
            code = 404,
            message = "Descriptive message for an applicant report not found"
        )
    })
    @GetMapping("/applicant/{applicantId}/bootcamp/{bootcampId}/download")
    public ResponseEntity<ApplicantDownloadReportDTO> getApplicantFile(
        @PathVariable("applicantId") long applicantId,
        @PathVariable("bootcampId") long bootcampId)
    {
        return ResponseEntity.ok(applicantReportFetchUseCase.getApplicantFile(
            applicantId,
            bootcampId));
    }
}
