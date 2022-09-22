package com.jalasoft.bootcamp.applicant.infrastructure.controller.applicant;

import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantDTO;
import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.fetch.ApplicantFetchUseCase;
import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.manipulation.ApplicantHandleUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan(basePackages="com.jalasoft.bootcamp.becommon.domain.exceptions")
@RestController
@RequestMapping("/applicant")
public class ApplicantController
{
    private static final Logger logger = LogManager.getLogger(ApplicantController.class);
    private final String ID = "id";
    private final ApplicantHandleUseCase applicantHandleUseCase;
    private final ApplicantFetchUseCase applicantFetchUseCase;

    @Autowired
    public ApplicantController(
        ApplicantHandleUseCase applicantHandleUseCase,
        ApplicantFetchUseCase applicantFetchUseCase)
    {
        this.applicantHandleUseCase = applicantHandleUseCase;
        this.applicantFetchUseCase = applicantFetchUseCase;
    }

    @ApiOperation(
        value = "Get an applicant",
        response = ApplicantDTO.class,
        notes = "Get an applicant given it's id, returns the below properties, fullName,"
            + " email, phoneNumber, birthday, photo, currentCity, career and degree"
    )
    @ApiResponses( {
        @ApiResponse(
            code = 200,
            message = "Successful applicant's profile obtained"
        ),
        @ApiResponse(
            code = 400,
            message = "Descriptive bad request message according the"
                + " rules violated"
        ),
        @ApiResponse(
            code = 404,
            message = "Descriptive message for an applicant not found"
        )
    })

    @GetMapping("/{id}")
    public ResponseEntity<ApplicantDTO> findById(@PathVariable(ID) long id)
    {
        logger.info("Find by id ", id);
        return ResponseEntity.ok(new ApplicantDTO(applicantFetchUseCase.findById(id)));
    }

    @ApiOperation(
        value = "Fetch all Applicants",
        response = ApplicantDTO.class,
        notes = "fetch all applicants"
    )
    @ApiResponses( {
        @ApiResponse(
            code = 200,
            message = "Fetch all applicants successfully"
        ),
        @ApiResponse(
            code = 400,
            message = "Descriptive bad request message according the rules violated"
        )
    })
    @GetMapping
    public ResponseEntity<List<ApplicantDTO>> getAllApplicants()
    {
        return ResponseEntity.ok(applicantFetchUseCase.fetchAll());
    }

    @ApiOperation(
        value = "Get all applicants excluding the bootcamp applicants",
        response = ApplicantDTO.class,
        notes = "Get all applicants excluding the bootcamp applicant by Bootcamp Id"
    )
    @ApiResponses( {
        @ApiResponse(
            code = 200, message = "Get all available applicants"
        ),
    })
    @GetMapping("/excluding-bootcamp-applicants/{bootcampId}")
    public ResponseEntity<List<ApplicantDTO>> getAllApplicantsExcludingABootcampApplicants(
        @RequestParam(required = false, value = "criteria") final String criteria, Sort sort,
        @PathVariable Long bootcampId)
    {
        List<ApplicantDTO> ApplicantDTOs = applicantFetchUseCase
            .getAllApplicantsExcludingBootcampApplicants(bootcampId, criteria,
                sort);
        logger.info("get all applicants ", ApplicantDTOs);
        return ResponseEntity.ok(ApplicantDTOs);
    }

    @ApiOperation(
        value = "Fetch all the applicants from a bootcamp",
        response = ApplicantDTO.class,
        notes = "Fetch all the applicants that are part of the specific bootcamp(the bootcamp id)"
    )
    @ApiResponses( {
        @ApiResponse(
            code = 200,
            message = "Successful applicants obtained from the specified bootcamp(bootcampId)"
        )
    })
    @GetMapping("/bootcamp/{bootcampId}")
    public ResponseEntity<Page<ApplicantDTO>> findAllApplicantsIncludedInBootcamp(
        @PathVariable("bootcampId") long bootcampId,
        @RequestParam(name = "unpaged") boolean unpaged,
        final Pageable pageable)
    {
        return ResponseEntity.ok(applicantFetchUseCase.fetchAllApplicantsIncludedInBootcamp(
            bootcampId, unpaged, pageable)
            .map(ApplicantDTO::new));
    }

    @ApiOperation(
        value = "Creates a new applicant",
        response = ApplicantDTO.class,
        notes =
            "Creates a new applicant given the below properties, name, birthday, email, telephone,"
                +
                "career, city, country, profession and degree"
    )
    @ApiResponses( {
        @ApiResponse(
            code = 201, message = "Created, when resource was created successfully and returns the"
            + " object of the created resource in the body"
        ),
        @ApiResponse(
            code = 400, message = "Descriptive bad request message according the rules violated"
        ),
        @ApiResponse(
            code = 409, message = "Duplicate email error, when there is already a registered"
            + " applicant with that email sent in the payload"
        ),
        @ApiResponse(
            code = 422, message = "Invalid input error, when the payload contains a required"
            + " attribute empty"
        )
    })
    @PostMapping
    public ResponseEntity<ApplicantDTO> create(
        @Valid @RequestBody final ApplicantDTO applicantToCreate)
    {
        ApplicantDTO applicantDTO =
            new ApplicantDTO(applicantHandleUseCase.create(applicantToCreate));
        logger.info("Applicant created ", applicantDTO);
        return new ResponseEntity<>(applicantDTO, HttpStatus.CREATED);
    }

    @ApiOperation(
        value = "Edit a applicant",
        response = ApplicantDTO.class,
        notes = "Edit a applicant given the below properties, name, cellphone"
            + ", country, city, university, degree, birthday, career and curriculumVitae are "
            + "required"
    )
    @ApiResponses( {
        @ApiResponse(
            code = 400,
            message = "Descriptive bad request message according the rules violated"
        ),
        @ApiResponse(
            code = 404,
            message = "Descriptive message for a Applicant not found"
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<ApplicantDTO> edit(
        @PathVariable(ID) long id,
        @RequestBody ApplicantDTO applicantDTO)
    {
        return ResponseEntity.ok(new ApplicantDTO(applicantHandleUseCase.edit(id, applicantDTO)));
    }
}
