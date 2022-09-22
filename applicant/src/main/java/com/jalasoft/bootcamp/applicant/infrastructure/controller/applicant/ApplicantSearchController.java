package com.jalasoft.bootcamp.applicant.infrastructure.controller.applicant;

import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantDTO;
import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.fetch.ApplicantFetchUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@ComponentScan(basePackages="com.jalasoft.bootcamp.becommon.domain.exceptions")
@RestController
public class ApplicantSearchController
{
    private final ApplicantFetchUseCase applicantFetchUseCase;

    @Autowired
    public ApplicantSearchController(ApplicantFetchUseCase applicantFetchUseCase)
    {
        this.applicantFetchUseCase = applicantFetchUseCase;
    }

    @GetMapping("/applicant/search")
    public List<ApplicantDTO> findApplicant(@RequestParam("value") final String value)
    {
        return applicantFetchUseCase.findByLocationCountry(value);
    }
}
