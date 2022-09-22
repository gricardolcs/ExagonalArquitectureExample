package com.jalasoft.bootcamp.bootcamp.infrastructure.controller.bootcamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.Bootcamp;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.bootcamp.BootcampDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.bootcamp.fetch.BootcampFetchUseCase;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class BootcampRetrievalControllerTest
{
    @Mock
    private BootcampFetchUseCase bootcampFetchUseCase;

    @InjectMocks
    private BootcampController bootcampController;

    private long id;
    private String name;
    private String description;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private int workflowType;
    private long workflowId;
    private int department;
    private long departmentId;
    private int participantsExpectedQuantity;
    private int projectType;
    private long projectTypeId;

    @BeforeEach
    void setUp()
    {
        id = UUID.randomUUID().getLeastSignificantBits();
        name = "Bolivian Bootcamp";
        description = "First Bolivian Bootcamp";
        location = "Av. Melchor Perez #123";
        startDate = LocalDate.of(2021, Month.JUNE, 20);
        endDate = LocalDate.of(2021, Month.DECEMBER, 20);
        workflowType = 1;
        workflowId = 1L;
        department = 1;
        departmentId = 1L;
        participantsExpectedQuantity = 30;
        projectType = 1;
        projectTypeId = 1L;
    }

    @Test
    void testFindBootcampById()
    {
        Bootcamp bootcamp = new Bootcamp(id, name,
            description, location,
            startDate, endDate, workflowType, workflowId,
            department, departmentId,
            participantsExpectedQuantity,
            new HashSet<>(), projectType, projectTypeId, Collections.emptySet());
        final long id = 10099001L;

        Mockito.when(bootcampFetchUseCase.findById(id)).thenReturn(new BootcampDTO(bootcamp));
        ResponseEntity<BootcampDTO> result = bootcampController.findBootcampById(id);
        BootcampDTO expectedBody = BootcampDTO.convert(bootcamp);
        BootcampDTO actualBody = result.getBody();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(expectedBody.getId(), actualBody.getId());
        assertEquals(expectedBody.getName(), actualBody.getName());
        assertEquals(expectedBody.getDescription(), actualBody.getDescription());
        assertEquals(expectedBody.getStartDate(), actualBody.getStartDate());
        assertEquals(expectedBody.getEndDate(), actualBody.getEndDate());
        assertEquals(expectedBody.getWorkflowType(), actualBody.getWorkflowType());
        assertEquals(expectedBody.getDepartmentType(), actualBody.getDepartmentType());
        assertEquals(expectedBody.getProjectType(), actualBody.getProjectType());
    }
}
