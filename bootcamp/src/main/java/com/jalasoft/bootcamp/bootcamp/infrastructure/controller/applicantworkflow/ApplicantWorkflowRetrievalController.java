package com.jalasoft.bootcamp.bootcamp.infrastructure.controller.applicantworkflow;

import com.jalasoft.bootcamp.bootcamp.domain.applicantworkflow.ApplicantWorkflow;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.applicantworkflow.ApplicantWorkflowDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.applicantworkflow.retrieve.ApplicantWorkflowRetrievalUseCase;
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
public class ApplicantWorkflowRetrievalController {

    private final ApplicantWorkflowRetrievalUseCase applicantWorkflowRetrievalUseCase;

    @Autowired
    public ApplicantWorkflowRetrievalController(
        ApplicantWorkflowRetrievalUseCase applicantWorkflowRetrievalUseCase) {
        this.applicantWorkflowRetrievalUseCase = applicantWorkflowRetrievalUseCase;
    }

    @ApiOperation(
        value = "Fetch the applicant workflow of an applicant",
        response = ApplicantWorkflowDTO.class,
        notes = "Fetches the applicant workflow information of an applicant, this includes the list "
            + "of stages in which the applicant was graded"
    )
    @ApiResponses({
        @ApiResponse(
            code = 200, message = "When the bootcamp and applicant with those ids exists, returns "
            + "an empty list for qualifications if the applicant was not qualified in any stage"
        ),
        @ApiResponse(
            code = 404, message = "If the bootcamp or applicant are not found in "
            + "our registers"
        ),
    })
    @GetMapping("/bootcamp/{bootcampId}/applicant/{applicantId}/workflow")
    public ResponseEntity<ApplicantWorkflowDTO> fetchApplicantWorkflow(
        @PathVariable("bootcampId") final long bootcampId,
        @PathVariable("applicantId") final long applicantId) {
        ApplicantWorkflow applicantWorkflow = applicantWorkflowRetrievalUseCase
            .retrieveApplicantWorkflowByBootcampIdAndApplicantId(bootcampId, applicantId);

        return ResponseEntity.ok(new ApplicantWorkflowDTO(applicantWorkflow));
    }
}
