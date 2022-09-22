package com.jalasoft.bootcamp.bootcamp.infrastructure.controller.applicantworkflow;

import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.bootcamp.BootcampDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.applicantBootcamp.ApplicantBootcampFetchAllUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@ComponentScan(basePackages="com.jalasoft.bootcamp.becommon.domain.exceptions")
@RestController
public class ApplicantBootcampController
{
    private final ApplicantBootcampFetchAllUseCase applicantBootcampFetchAllUseCase;

    @Autowired
    public ApplicantBootcampController(ApplicantBootcampFetchAllUseCase applicantBootcampFetchAllUseCase)
    {
        this.applicantBootcampFetchAllUseCase = applicantBootcampFetchAllUseCase;
    }

    @GetMapping("applicant/{applicantId}/bootcamp")
    public ResponseEntity<List<BootcampDTO>> getApplicantWorkflowsByApplicantId(
        @PathVariable("applicantId") long applicantId)
    {
        return new ResponseEntity<>(
            applicantBootcampFetchAllUseCase.getAllByApplicantId(applicantId), HttpStatus.OK);
    }
}
