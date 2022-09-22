package com.jalasoft.bootcamp.bootcamp.infrastructure.controller.bootcamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.Bootcamp;
import com.jalasoft.bootcamp.becommon.domain.exceptions.InvalidArgumentsEntityException;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.bootcamp.BootcampDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.bootcamp.manipulation.BootcampHandleUseCase;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
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
class BootcampCreationControllerTest
{
    @Mock
    private BootcampHandleUseCase bootcampHandleUseCase;

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
    private Bootcamp bootcamp;

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
        bootcamp = new Bootcamp(id, name, description,
            location, startDate, endDate,
            workflowType, workflowId, department, departmentId,
            participantsExpectedQuantity, new HashSet<>(),
            projectType, projectTypeId, Collections.emptySet());
    }

    @Test
    void testValidCreateBootcamp()
    {
        BootcampDTO bootcampDTO = new BootcampDTO(bootcamp);
        BootcampDTO bootcampCreationDTO = new BootcampDTO(bootcamp);

        Mockito.when(bootcampHandleUseCase.create(bootcampCreationDTO))
            .thenReturn(bootcampDTO);
        ResponseEntity<BootcampDTO> result = bootcampController.create(bootcampCreationDTO);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(201, result.getStatusCodeValue());
        assertEquals(bootcamp.getId(), result.getBody().getId());
        assertEquals(
            bootcampCreationDTO.getName(),
            result.getBody().getName());
        assertEquals(
            bootcampCreationDTO.getDescription(),
            result.getBody().getDescription());
        assertEquals(
            bootcampCreationDTO.getLocation(),
            result.getBody().getLocation());
        assertEquals(
            bootcampCreationDTO.getStartDate(),
            result.getBody().getStartDate());
        assertEquals(
            bootcampCreationDTO.getEndDate(),
            result.getBody().getEndDate());
        assertEquals(
            bootcampCreationDTO.getWorkflowType(),
            result.getBody().getWorkflowType());
        assertEquals(
            bootcampCreationDTO.getParticipantsExpectedQuantity(),
            result.getBody().getParticipantsExpectedQuantity());
        assertEquals(
            bootcampCreationDTO.getProjectType(),
            result.getBody().getProjectType());
    }

    @Test
    void testInvalidNumberOfExpectedParticipantsCreateBootcamp()
    {
        int negativeParticipantsExpectedQuantity = -100;

        bootcamp.changeBootcampParticipantsExpectedQuantity(
            negativeParticipantsExpectedQuantity);
        BootcampDTO bootcampCreationDTO = new BootcampDTO(bootcamp);

        Mockito.when(bootcampHandleUseCase.create(bootcampCreationDTO))
            .thenThrow(InvalidArgumentsEntityException.class);

        Assertions.assertThrows(
            InvalidArgumentsEntityException.class,
            () -> bootcampController
                .create(bootcampCreationDTO));
    }
}