package com.jalasoft.bootcamp.applicant.infrastructure.controller.applicant;

import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantReportDTO;
import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.upload.ApplicantUploadReportUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@ComponentScan(basePackages="com.jalasoft.bootcamp.becommon.domain.exceptions")
@RestController
public class ApplicantUploadReportController
{
    private final ApplicantUploadReportUseCase applicantUploadReportUseCase;

    @Autowired
    public ApplicantUploadReportController(final ApplicantUploadReportUseCase applicantUploadReportUseCase)
    {
        this.applicantUploadReportUseCase = applicantUploadReportUseCase;
    }

    @ApiOperation(
        value = "Upload report from a csv, pdf, png or doc file",
        response = ApplicantReportDTO.class,
        notes = "Upload report from a file to the database, the file can be an image or a document "
            +
            "the size of the file should not exceed 5mb"
    )
    @ApiResponses( {
        @ApiResponse(
            code = 200, message = "Created when the report is successfully loaded and saved in "
            + "the database"
        ),
        @ApiResponse(
            code = 415, message = "Invalid file error when the file is not in csv, pdf, png, " +
            "doc format or cannot be parsed, message: Please verify that your file is in the "
            + "correct format"
        )
    })
    @PostMapping("/applicant/{applicantId}/bootcamp/{bootcampId}/report")
    public ResponseEntity<ApplicantReportDTO> uploadApplicants(
        @PathVariable("applicantId") long applicantId,
        @PathVariable("bootcampId") long bootcampId, @RequestParam("file") MultipartFile file)
    throws IOException
    {
        return ResponseEntity.ok(applicantUploadReportUseCase.uploadReport(
            file,
            applicantId,
            bootcampId));
    }
}
