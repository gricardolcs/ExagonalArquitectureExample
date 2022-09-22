package com.jalasoft.bootcamp.bootcamp.infrastructure.controller.bootcamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.Bootcamp;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.bootcamp.BootcampDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.bootcamp.fetch.BootcampFetchUseCase;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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
class BootcampFetchAllControllerTest
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
        location = "Av. Melchor Perez";
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
    void testValidFetchEmpty()
    {
        List<BootcampDTO> expectedBody = new ArrayList<>();

        Mockito.when(bootcampFetchUseCase.fetchAll())
            .thenReturn(expectedBody);
        ResponseEntity<List<BootcampDTO>> result = bootcampController
            .findBootcampFetchAll();
        List<BootcampDTO> actualBody = result.getBody();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(expectedBody.size(), actualBody.size());
        assertEquals(expectedBody, actualBody);
    }

    @Test
    void testValidFetchBootcamps()
    {
        Bootcamp bootcamp = new Bootcamp(id, name,
            description, location,
            startDate, endDate, workflowType, workflowId,
            department, departmentId,
            participantsExpectedQuantity,
            new HashSet<>(), projectType, projectTypeId, Collections.emptySet());
        List<BootcampDTO> expectedBody = new ArrayList<>();

        expectedBody.add(new BootcampDTO(bootcamp));
        Mockito.when(bootcampFetchUseCase.fetchAll())
            .thenReturn(expectedBody);
        ResponseEntity<List<BootcampDTO>> result = bootcampController
            .findBootcampFetchAll();
        List<BootcampDTO> actualBody = result.getBody();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(expectedBody.size(), actualBody.size());
        assertEquals(expectedBody.get(0).getId(), actualBody.get(0).getId());
        assertEquals(expectedBody.get(0).getName(), actualBody.get(0).getName());
        assertEquals(expectedBody.get(0).getDescription(), actualBody.get(0).getDescription());
        assertEquals(expectedBody.get(0).getStartDate(), actualBody.get(0).getStartDate());
        assertEquals(expectedBody.get(0).getEndDate(), actualBody.get(0).getEndDate());
        assertEquals(expectedBody.get(0).getWorkflowType(), actualBody.get(0).getWorkflowType());
        assertEquals(expectedBody.get(0).getDepartment(), actualBody.get(0).getDepartment());
        assertEquals(expectedBody.get(0).getProjectType(), actualBody.get(0).getProjectType());
    }
}
