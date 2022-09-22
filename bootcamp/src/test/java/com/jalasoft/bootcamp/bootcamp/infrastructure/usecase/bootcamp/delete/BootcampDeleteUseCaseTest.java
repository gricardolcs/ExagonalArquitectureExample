package com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.bootcamp.delete;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.Bootcamp;
import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.BootcampRepository;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.bootcamp.manipulation.BootcampHandleUseCase;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class BootcampDeleteUseCaseTest
{
    @Spy
    private BootcampRepository bootcampRepository;

    @InjectMocks
    private BootcampHandleUseCase bootcampHandleUseCase;

    private long bootcampId;
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
        MockitoAnnotations.initMocks(this);
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
        participantsExpectedQuantity = 30;
        projectType = 1;
        projectTypeId = 1L;
    }

    @Test
    public void testDeleteWithACorrectBootcampId()
    {
        Bootcamp bootcamp = new Bootcamp(bootcampId, name,
            description, location,
            startDate, endDate, workflowType, workflowId,
            department, departmentId,
            participantsExpectedQuantity,
            new HashSet<>(), projectType, projectTypeId, Collections.emptySet());

        Mockito.when(bootcampRepository.findById(bootcampId)).thenReturn(Optional.of(bootcamp));
        bootcampHandleUseCase.delete(bootcampId);

        Mockito.verify(bootcampRepository).deleteById(bootcampId);
    }

    @Test
    public void testDeleteWithAnIncorrectBootcampId()
    {
        Mockito.when(bootcampRepository.findById(bootcampId)).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(
            EntityNotFoundException.class,
            () -> bootcampHandleUseCase.delete(bootcampId));
        assertSame(ErrorsUtil.ERROR_MESSAGE_ENTITY_NOT_FOUND, exception.getErrorMessage());
    }
}
