package com.jalasoft.bootcamp.bootcamp.infrastructure.controller.applicantworkflow;

import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualification;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.applicantworkflow.ApplicantQualificationDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.applicantworkflow.retrieve.ApplicantStageQualificationRetrievalUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan(basePackages="com.jalasoft.bootcamp.becommon.domain.exceptions")
@RestController
public class ApplicantStageQualificationRetrievalController
{
    private final ApplicantStageQualificationRetrievalUseCase applicantStageQualificationRetrievalUseCase;

    public ApplicantStageQualificationRetrievalController(
        ApplicantStageQualificationRetrievalUseCase applicantStageQualificationRetrievalUseCase)
    {
        this.applicantStageQualificationRetrievalUseCase =
            applicantStageQualificationRetrievalUseCase;
    }

    @ApiOperation(
        value = "Fetches a stage qualification of an Applicant",
        response = ApplicantQualificationDTO.class,
        notes = "Fetches an specific stage qualification of an applicant by the applicant id, "
            + "stage id and bootcamp id"
    )
    @ApiResponses( {
        @ApiResponse(
            code = 200, message = "When the bootcamp, stage and applicant with these ids exists, "
            + "returns the Stage Qualification of an applicant"
        ),
        @ApiResponse(
            code = 404, message = "If the bootcamp, stage or applicant are not found in "
            + "our registers"
        ),
    })
    @GetMapping("/bootcamp/{bootcampId}/applicant/{applicantId}/stage/{stageId}/qualification")
    public ResponseEntity<ApplicantQualificationDTO> fetchApplicantStageQualification(
        @PathVariable("bootcampId") final long bootcampId,
        @PathVariable("applicantId") final long applicantId,
        @PathVariable("stageId") final long stageId)
    {
        ApplicantStageQualification applicantStageQualification =
            applicantStageQualificationRetrievalUseCase
                .retrieveApplicantStageQualificationByApplicantIdAndStageId(bootcampId, applicantId,
                    stageId);

        return ResponseEntity.ok(new ApplicantQualificationDTO(applicantStageQualification));
    }
}
