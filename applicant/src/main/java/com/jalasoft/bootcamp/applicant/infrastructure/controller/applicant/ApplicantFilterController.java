package com.jalasoft.bootcamp.applicant.infrastructure.controller.applicant;

import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantDTO;
import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.fetch.ApplicantFetchUseCase;
import com.jalasoft.bootcamp.applicant.infrastructure.utils.Filter;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan(basePackages="com.jalasoft.bootcamp.becommon.domain.exceptions")
@RestController
public class ApplicantFilterController
{
    private final ApplicantFetchUseCase applicantFetchUseCase;

    @Autowired
    public ApplicantFilterController(ApplicantFetchUseCase applicantFetchUseCase)
    {
        this.applicantFetchUseCase = applicantFetchUseCase;
    }

    @ApiResponses( {
        @ApiResponse(
            code = 400,
            message = "Descriptive bad request message according the rules violated"
        )
    })
    @PostMapping("/applicant/filter")
    public ResponseEntity<List<ApplicantDTO>> filterApplicant(
        @RequestParam(name = "bootcampId", required = false) Long bootcampId,
        @Valid @RequestBody final List<Filter> filters)
    {
        return ResponseEntity.ok(applicantFetchUseCase.findByFilters(bootcampId, filters));
    }

}
