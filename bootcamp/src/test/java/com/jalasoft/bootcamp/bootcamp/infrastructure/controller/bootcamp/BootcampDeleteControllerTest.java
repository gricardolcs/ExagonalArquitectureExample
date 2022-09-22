package com.jalasoft.bootcamp.bootcamp.infrastructure.controller.bootcamp;

import static org.junit.jupiter.api.Assertions.assertSame;

import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.Bootcamp;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.bootcamp.manipulation.BootcampHandleUseCase;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
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
public class BootcampDeleteControllerTest
{
    @Mock
    private BootcampHandleUseCase bootcampHandleUseCase;

    @InjectMocks
    private BootcampController bootcampController;

    private Long bootcampId;
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
        bootcampId = -516744694L;
        name = "Bootcamp DevOps";
        description = "Bootcamp #02 DevOps - 2021";
        location = "Av. Melchor Perez #123";
        startDate = LocalDate.of(2021, 04, 10);
        endDate = LocalDate.of(2021, 07, 10);
        workflowType = 1;
        workflowId = 1L;
        department = 1;
        departmentId = 1L;
        projectType = 1;
        projectTypeId = 1L;
        participantsExpectedQuantity = 30;
    }

    @Test
    public void testDeleteACorrectBootcampId()
    {
        Bootcamp bootcamp = new Bootcamp(bootcampId, name,
            description, location,
            startDate, endDate, workflowType, workflowId,
            department, departmentId,
            participantsExpectedQuantity,
            new HashSet<>(), projectType, projectTypeId, Collections.emptySet());

        Mockito.when(bootcampHandleUseCase.delete(bootcampId)).thenReturn(bootcamp);
        ResponseEntity<String> responseEntity = bootcampController.delete(bootcampId);
        String expectedBody = "Bootcamp DevOps";

        assertSame(expectedBody, responseEntity.getBody());
        assertSame(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
