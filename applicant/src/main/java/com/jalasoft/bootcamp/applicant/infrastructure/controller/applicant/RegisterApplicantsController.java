package com.jalasoft.bootcamp.applicant.infrastructure.controller.applicant;

import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantRegisteredAtBootcampsDTO;
import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantRegisteredInBootcampDTO;
import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.update.RegisterApplicantUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ComponentScan(basePackages="com.jalasoft.bootcamp.becommon.domain.exceptions")
@RestController
@RequestMapping("/register")
public class RegisterApplicantsController {
    private final RegisterApplicantUseCase registerApplicantUseCase;

    @Autowired
    public RegisterApplicantsController(
            RegisterApplicantUseCase registerApplicantUseCase) {
        this.registerApplicantUseCase =
                registerApplicantUseCase;
    }

    @ApiOperation(
            value = "Register applicant at different bootcamp",
            response = ApplicantRegisteredAtBootcampsDTO.class,
            notes = "To register need the applicant id and the ids list selected of the bootcamps"
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200, message = "Response with an bootcamp ids list"
            ),
            @ApiResponse(
                    code = 404, message = "The applicant doesn't exist"
            ),
    })
    @PutMapping("/{id}/register-at-bootcamps")
    public ResponseEntity<ApplicantRegisteredAtBootcampsDTO> registerApplicantAtBootcamps(
            @PathVariable("id") Long id,
            @RequestBody List<Long> bootcampIds) {
        return ResponseEntity.ok(registerApplicantUseCase.registerApplicantInBootcamps(
                id,
                bootcampIds));
    }

    @ApiOperation(
            value = "Register all applicants to a bootcamp",
            response = ApplicantRegisteredInBootcampDTO.class,
            notes = "To register needs the bootcamp id to add the new applicants and the applicants "
                    + "ids list selected"
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200, message = "Response with an applicants ids list"
            ),
            @ApiResponse(
                    code = 404, message = "The bootcamp doesn't exist"
            ),
    })
    @PutMapping("/applicants-in-bootcamp/{bootcampId}")
    public ResponseEntity<ApplicantRegisteredInBootcampDTO> registerApplicantsInBootcamp(
            @PathVariable Long bootcampId,@RequestBody List<Long> existingApplicantIds)
    {
        ApplicantRegisteredInBootcampDTO applicantRegisteredInBootcampDTO =
                registerApplicantUseCase
                        .registerApplicantsInBootcamp(existingApplicantIds, bootcampId);
        return ResponseEntity.ok(applicantRegisteredInBootcampDTO);
    }
}
