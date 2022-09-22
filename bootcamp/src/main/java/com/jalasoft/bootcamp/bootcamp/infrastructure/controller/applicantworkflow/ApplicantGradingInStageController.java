package com.jalasoft.bootcamp.bootcamp.infrastructure.controller.applicantworkflow;

import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualification;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.applicantstagequalification.ApplicantStageQualificationCreationDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.applicantstagequalification.ApplicantStageQualificationDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.applicantworkflow.grading.ApplicantGradingInStageUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan(basePackages="com.jalasoft.bootcamp.becommon.domain.exceptions")
@RestController
public class ApplicantGradingInStageController
{

    @ApiOperation(
        value = "Grades an applicant in a specific stage",
        response = ApplicantStageQualificationDTO.class,
        notes = "Grades an applicant in a specific stage inside a Bootcamp if the stage is the "
            + "first in the Bootcamp a new Applicant Workflow is created for the applicant with "
            + "the stage linked to that workflow, also if the status of the qualification is "
            + "PASSED so the next stage is linked to its Workflow"
    )
    @ApiResponses( {
        @ApiResponse(
            code = 200, message = "When the applicant was qualified successfully"
        ),
        @ApiResponse(
            code = 404, message = "If the bootcamp or stage or applicant was not found in "
            + "our registers"
        ),
    })
    @PostMapping("/bootcamp/{bootcampId}/stage/{stageId}/applicant/{applicantId}/grade")
    public ResponseEntity<ApplicantStageQualificationDTO> gradeApplicant(
        @PathVariable("bootcampId") final long bootcampId,
        @PathVariable("stageId") final long stageId,
        @PathVariable("applicantId") final long applicantId,
        @RequestBody ApplicantStageQualificationCreationDTO
        applicantStageQualificationCreationDTO)
    {

        ApplicantStageQualification savedApplicantStageQualification =
            applicantGradingInStageUseCase
            .gradeApplicantInStage(bootcampId, stageId, applicantId,
                applicantStageQualificationCreationDTO);

        return ResponseEntity.ok(
            new ApplicantStageQualificationDTO(applicantId, stageId,
                savedApplicantStageQualification.getQualificationStatus()));
    }

    private final ApplicantGradingInStageUseCase applicantGradingInStageUseCase;

    @Autowired
    public ApplicantGradingInStageController(
        ApplicantGradingInStageUseCase applicantGradingInStageUseCase)
    {
        this.applicantGradingInStageUseCase = applicantGradingInStageUseCase;
    }
}
