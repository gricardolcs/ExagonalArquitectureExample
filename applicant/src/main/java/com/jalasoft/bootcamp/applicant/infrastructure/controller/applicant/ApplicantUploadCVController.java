package com.jalasoft.bootcamp.applicant.infrastructure.controller.applicant;

import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantDTO;
import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.upload.ApplicantUploadCVUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@ComponentScan(basePackages="com.jalasoft.bootcamp.becommon.domain.exceptions")
@RestController
public class ApplicantUploadCVController
{
    private final ApplicantUploadCVUseCase applicantUploadCVUseCase;

    @Autowired
    public ApplicantUploadCVController(final ApplicantUploadCVUseCase applicantUploadCVUseCase)
    {
        this.applicantUploadCVUseCase = applicantUploadCVUseCase;
    }

    @ApiOperation(
        value = "Upload Curriculum Vitae , pdf or docx file",
        response = ApplicantDTO.class,
        notes = "Upload CV from a file to the database, the file must be a document the size of "
            + "the file should not exceed 5mb"
    )
    @ApiResponses( {
        @ApiResponse(
            code = 200, message = "Created when the CV is successfully loaded and saved in "
            + "the database"
        ),
        @ApiResponse(
            code = 415, message = "Invalid file error when the file is not in pdf or doc format "
            + "or cannot be parsed, message: Please verify that your file is in the "
            + "correct format"
        )
    })
    @PostMapping("/applicant/{applicantId}/cv")
    public ResponseEntity<ApplicantDTO> uploadApplicants(
        @PathVariable("applicantId") long applicantId,
        @RequestParam("file") MultipartFile file) throws IOException
    {
        return ResponseEntity.ok(applicantUploadCVUseCase.uploadCV(applicantId, file));
    }
}
