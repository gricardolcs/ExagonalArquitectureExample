package com.jalasoft.bootcamp.bootcamp.infrastructure.controller.bootcamp;

import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.Bootcamp;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.bootcamp.BootcampDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.bootcamp.fetch.BootcampFetchUseCase;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.bootcamp.manipulation.BootcampHandleUseCase;
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
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/bootcamp")
public class BootcampController
{
    private final String ID = "id";
    private final BootcampFetchUseCase bootcampFetchUseCase;
    private final BootcampHandleUseCase bootcampHandleUseCase;
    private static final Logger logger = LogManager.getLogger(BootcampController.class);

    @Autowired
    public BootcampController(
        BootcampFetchUseCase bootcampFetchUseCase,
        BootcampHandleUseCase bootcampHandleUseCase)
    {
        this.bootcampFetchUseCase = bootcampFetchUseCase;
        this.bootcampHandleUseCase = bootcampHandleUseCase;
    }

    @ApiOperation(
        value = "Retrieve a bootcamp",
        response = BootcampDTO.class,
        notes = "Gets a bootcamp by its id"
    )
    @ApiResponses( {
        @ApiResponse(
            code = 200, message = "Successful bootcamp retrieval"
        ),
        @ApiResponse(
            code = 400, message = "Descriptive bad request message according the rules violated"
        )
    })
    @GetMapping("/{id}")
    ResponseEntity<BootcampDTO> findBootcampById(@PathVariable final long id)
    {
        logger.error("Find by id ", id);
        return ResponseEntity.ok(bootcampFetchUseCase.findById(id));
    }

    @ApiOperation(
        value = "Fetch all bootcamps",
        response = BootcampDTO.class,
        notes = "Fetch all bootcamps"
    )
    @ApiResponses( {
        @ApiResponse(
            code = 200, message = "Fetch all bootcamps successfully"
        ),
        @ApiResponse(
            code = 400, message = "Descriptive bad request message according the rules violated"
        )
    })
    @GetMapping
    ResponseEntity<List<BootcampDTO>> findBootcampFetchAll()
    {
        return new ResponseEntity<>(bootcampFetchUseCase.fetchAll(), HttpStatus.OK);
    }

    @ApiOperation(
        value = "Fetch bootcamps with pagination and sorting",
        response = BootcampDTO.class,
        notes = "Fetch bootcamps with pagination and sorting"
    )
    @ApiResponses( {
        @ApiResponse(
            code = 200,
            message = "Fetch bootcamps with pagination and sorting successfully"
        )
    })
    @GetMapping("/pagination")
    public ResponseEntity<Page<BootcampDTO>> fetch(final Pageable pageable)
    {
        Page<BootcampDTO> bootcampDTOPage = bootcampFetchUseCase.fetch(pageable);
        return ResponseEntity.ok(bootcampDTOPage);
    }

    @ApiOperation(
        value = "Fetch bootcamp by name",
        response = BootcampDTO.class,
        notes = "Fetch bootcamp by name"
    )
    @ApiResponses( {
        @ApiResponse(
            code = 200, message = "Fetch bootcamp by name successfully"
        ),
        @ApiResponse(
            code = 400, message = "Descriptive bad request message according the rules violated"
        )
    })
    @GetMapping("/search")
    ResponseEntity<List<BootcampDTO>> findBootcampByName(@RequestParam("name") final String name)
    {
        logger.info("Find by name ", name);
        return new ResponseEntity<>(bootcampFetchUseCase.findBootcampByName(name), HttpStatus.OK);
    }

    @ApiOperation(
        value = "Fetch all bootcamps by criteria",
        response = BootcampDTO.class,
        notes = "Fetch all bootcamps that match the filter criteria"
    )
    @ApiResponses( {
        @ApiResponse(
            code = 200,
            message = "Successful bootcamps obtained from a filter criteria"
        )
    })
    @GetMapping("/filter")
    public ResponseEntity<List<BootcampDTO>> filterApplicant(
        @RequestParam("criteria") final String criteria)
    {
        return ResponseEntity.ok(bootcampFetchUseCase.findByCriteria(criteria));
    }

    @ApiOperation(
        value = "Get all bootcamps excluding the bootcamps with applicant",
        response = BootcampDTO.class,
        notes = "Get all bootcamps excluding the bootcamps by Applicant Id"
    )
    @ApiResponses( {
        @ApiResponse(
            code = 200, message = "Get all available bootcamps"
        ),
    })
    @GetMapping("/excluding-applicant-bootcamps/{applicantId}")
    public ResponseEntity<List<BootcampDTO>> getAllBootcampsExcludingAnApplicant(
        @RequestParam(required = false,defaultValue = "", value = "nameCriteria") final String nameCriteria, Sort sort,
        @PathVariable Long applicantId)
    {
        List<BootcampDTO> bootcampDTOList = bootcampFetchUseCase
            .getAllBootcampsExcludingApplicant(applicantId, nameCriteria,
                sort);
        logger.info("get all available bootcamps ", bootcampDTOList);
        return ResponseEntity.ok(bootcampDTOList);
    }

    @ApiOperation(
        value = "Creates a new bootcamp",
        response = BootcampDTO.class,
        notes = "Creates a new bootcamp given the below properties, name, startDate, endDate"
            + " and workflowType are required"
    )
    @ApiResponses( {
        @ApiResponse(
            code = 201, message = "Created, when resource was created successfully and returns the"
            + " id of the created resource in the body"
        ),
        @ApiResponse(
            code = 400, message = "Descriptive bad request message according the rules violated"
        ),
        @ApiResponse(
            code = 409, message = "Duplicate name error, when there is already a registered"
            + " Bootcamp with that name sent in the payload"
        ),
        @ApiResponse(
            code = 422, message = "Invalid input error, when the payload contains a required"
            + " attribute empty"
        )
    })
    @PostMapping
    public ResponseEntity<BootcampDTO> create(
        @Valid @RequestBody final BootcampDTO bootcampCreationDTO)
    {
        return new ResponseEntity<>(bootcampHandleUseCase.create(bootcampCreationDTO),
            HttpStatus.CREATED);
    }

    @ApiOperation(
        value = "Edit a bootcamp",
        response = BootcampDTO.class,
        notes = "Edit a bootcamp given the below properties, name, startDate, endDate"
            + ", description and workflowType are required"
    )
    @ApiResponses( {
        @ApiResponse(
            code = 400,
            message = "Descriptive bad request message according the rules violated"
        ),
        @ApiResponse(
            code = 404,
            message = "Descriptive message for a Bootcamp not found"
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<BootcampDTO> edit(
        @PathVariable(ID) long id, @RequestBody BootcampDTO bootcampEditionDTO)
    {
        return ResponseEntity.ok(bootcampHandleUseCase.edit(id, bootcampEditionDTO));
    }

    @ApiOperation(
        value = "Delete a bootcamp",
        notes = "Delete a bootcamp given the id"
    )

    @ApiResponses( {
        @ApiResponse(
            code = 200, message = "Delete, The request has been successfully and returns the "
            + "bootcamp´s name"
        ),
        @ApiResponse(
            code = 400, message = "Descriptive bad request message according the rules violated"
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(ID) long id)
    {
        logger.info("Record deleted ", id);
        Bootcamp bootcamp = bootcampHandleUseCase.delete(id);
        return ResponseEntity.ok().body(bootcamp.getName());
    }
}
