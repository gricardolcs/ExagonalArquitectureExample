package com.jalasoft.bootcamp.bootcamp.infrastructure.controller.bootcamp;

import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.applicantworkflow.ApplicantWorkflowDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.applicantworkflow.retrieve.ApplicantWorkflowsRetrievalUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan(basePackages="com.jalasoft.bootcamp.becommon.domain.exceptions")
@RestController
public class ApplicantWorkflowsRetrievalController
{
    private final ApplicantWorkflowsRetrievalUseCase applicantWorkflowsRetrievalUseCase;

    @Autowired
    public ApplicantWorkflowsRetrievalController(
        ApplicantWorkflowsRetrievalUseCase applicantWorkflowsRetrievalUseCase)
    {
        this.applicantWorkflowsRetrievalUseCase = applicantWorkflowsRetrievalUseCase;
    }

    @ApiOperation(
        value = "Retrieval Applicant Workflows",
        response = ApplicantWorkflowDTO.class,
        notes = "Retrieval Applicant Workflows"
    )
    @ApiResponses( {
        @ApiResponse(
            code = 200, message = "Retrieval Applicant Workflows successfully"
        ),
    })
    @GetMapping("applicant/{applicantId}/workflow")
    public ResponseEntity<List<ApplicantWorkflowDTO>> getApplicantWorkflowsByApplicantId(
        @PathVariable("applicantId") long applicantId)
    {
        return new ResponseEntity<>(
            ApplicantWorkflowDTO.convert(applicantWorkflowsRetrievalUseCase
                .fetchAllApplicantWorkflowByApplicantId(applicantId)), HttpStatus.OK);
    }
}
