package com.jalasoft.bootcamp.applicant.infrastructure.controller.applicant;

import com.jalasoft.bootcamp.becommon.domain.exceptions.InvalidFileException;
import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantUploadDTO;
import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.upload.ApplicantUploadUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;

@ComponentScan(basePackages="com.jalasoft.bootcamp.becommon.domain.exceptions")
@RestController
public class ApplicantUploadController
{
    private final ApplicantUploadUseCase applicantUploadUseCase;

    @Autowired
    public ApplicantUploadController(final ApplicantUploadUseCase applicantUploadUseCase)
    {
        this.applicantUploadUseCase = applicantUploadUseCase;
    }

    @ApiOperation(
        value = "Upload applicants from a csv file",
        response = ApplicantUploadDTO.class,
        notes =
            "Upload applicants from a csv file to the database, the file must have the first row as"
                +
                "header and must contains the required attributes in the headers and in the values"
    )
    @ApiResponses( {
        @ApiResponse(
            code = 200, message = "Created when applicants were uploaded and saved successfully" +
            " to the database and also includes the ones who were not saved and a message "
            + "describing it"
        ),
        @ApiResponse(
            code = 415, message = "Invalid file error when the file doesn't have a csv format or" +
            " the csv's data cannot be parsed, message: Please verify that your file has the "
            + "correct format"
        )
    })
    @PostMapping("/applicant/import")
    public ResponseEntity<ApplicantUploadDTO> uploadApplicants(
        @RequestParam("file") MultipartFile file,
        @RequestParam("bootcampId") long bootcampId)
    {
        try
        {
            return ResponseEntity.ok(applicantUploadUseCase.uploadApplicants(
                new InputStreamReader(file.getInputStream()), bootcampId));
        }
        catch (IOException | NumberFormatException e)
        {
            throw new InvalidFileException(file.getOriginalFilename(), e.toString());
        }
    }
}
