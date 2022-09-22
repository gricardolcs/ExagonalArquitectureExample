package com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.bootcamp.edition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.Bootcamp;
import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.BootcampRepository;
import com.jalasoft.bootcamp.bootcamp.domain.department.Department;
import com.jalasoft.bootcamp.becommon.domain.exceptions.DuplicatedEntityException;
import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.bootcamp.domain.projecttype.ProjectType;
import com.jalasoft.bootcamp.bootcamp.domain.projecttype.ProjectTypeName;
import com.jalasoft.bootcamp.bootcamp.domain.workflow.Workflow;
import com.jalasoft.bootcamp.bootcamp.domain.workflow.WorkflowName;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.bootcamp.BootcampDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.bootcamp.manipulation.BootcampHandleUseCase;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.department.DepartmentDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.projecttype.ProjectTypeDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.workflow.WorkflowDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.utils.BootcampDTOBuilder;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BootcampEditionUseCaseTest
{
    @Mock
    private BootcampRepository bootcampRepository;

    @Mock
    private BootcampDTOBuilder bootcampDTOBuilder;

    @InjectMocks
    private BootcampHandleUseCase bootcampHandleUseCase;

    private long bootcampId;
    private long workflowId;
    private long departmentId;
    private long projectTypeId;
    private Bootcamp bootcamp;

    @BeforeEach
    void setUp()
    {
        bootcampId = UUID.randomUUID().getLeastSignificantBits();
        workflowId = 1L;
        departmentId = 1L;
        projectTypeId = 1L;
        String name = "Bolivian Bootcamp";
        String description = "First Bolivian Bootcamp";
        String location = "Av. Melchor Perez #123";
        LocalDate startDate = LocalDate.of(2021, Month.JUNE, 20);
        LocalDate endDate = LocalDate.of(2021, Month.DECEMBER, 20);
        int workflowType = 1;
        int department = 1;
        int participantsExpectedQuantity = 30;
        int projectType = 1;
        bootcamp = new Bootcamp(bootcampId, name,
            description, location,
            startDate, endDate, workflowType, workflowId,
            department, departmentId,
            participantsExpectedQuantity, new HashSet<>(),
            projectType, projectTypeId, Collections.emptySet());
    }

    @Test
    public void testEditWithACorrectBootcamp()
    {
        BootcampDTO bootcampEditionDTO = new BootcampDTO(bootcamp);
        BootcampDTO expectedResult = new BootcampDTO(bootcamp);
        Workflow workflow = new Workflow(workflowId, new WorkflowName("Dev Workflow"),
            Collections.emptyList());
        Department department = new Department(departmentId, "QA", "This is the description de QA department");
        ProjectType projectType = new ProjectType(projectTypeId, new ProjectTypeName("Bootcamp"));
        expectedResult.setWorkflow(new WorkflowDTO(workflow))
            .setDepartment(new DepartmentDTO(department))
            .setProject(new ProjectTypeDTO(projectType));

        Mockito.when(bootcampRepository.findById(bootcampId)).thenReturn(Optional.of(bootcamp));
        Mockito.when(bootcampRepository.save(bootcamp)).thenReturn(bootcamp);
        Mockito.when(bootcampDTOBuilder.build(bootcamp)).thenReturn(expectedResult);
        BootcampDTO actualResult = bootcampHandleUseCase.edit(bootcampId, bootcampEditionDTO);

        verify(bootcampRepository).save(bootcamp);
        assertEquals(expectedResult.getId(), actualResult.getId());
        assertEquals(expectedResult.getName(), actualResult.getName());
        assertEquals(expectedResult.getDescription(), actualResult.getDescription());
        assertEquals(expectedResult.getStartDate(), actualResult.getStartDate());
        assertEquals(expectedResult.getEndDate(), actualResult.getEndDate());
        assertEquals(expectedResult.getWorkflowType(), actualResult.getWorkflowType());
        assertEquals(expectedResult.getDepartmentType(), actualResult.getDepartmentType());
        assertEquals(expectedResult.getProjectType(), actualResult.getProjectType());
        assertEquals(expectedResult.getWorkflow(), actualResult.getWorkflow());
        assertEquals(expectedResult.getDepartment(), actualResult.getDepartment());
        assertEquals(expectedResult.getProject(), actualResult.getProject());
    }

    @Test
    public void testEditWithAExistentName()
    {
        Bootcamp editedBootcamp = new Bootcamp(bootcampId,
            "Tech Elevator",
            "Tech Elevator offers a coding bootcamp dedicated to readying students.",
            "Av. Melchor Perez #123",
            LocalDate.of(2021, 3, 12),
            LocalDate.of(2021, 7, 15), 1, 1L, 1, 1L,
            30, new HashSet<>(), 1, 1L,
            Collections.emptySet());
        BootcampDTO bootcampEditionDTO = new BootcampDTO(editedBootcamp);
        when(bootcampRepository.existsByNameIgnoreCaseAndIdIsNot("Tech Elevator",
            bootcampId)).thenReturn(Boolean.TRUE);

        DuplicatedEntityException exception = assertThrows(DuplicatedEntityException.class
            , () -> bootcampHandleUseCase.edit(bootcampId, bootcampEditionDTO));
        assertEquals(ErrorsUtil.ERROR_MESSAGE_ENTITY_DUPLICATED, exception.getErrorMessage());
    }

    @Test
    public void testEditWithANonExistentId()
    {
        Bootcamp bootcamp = new Bootcamp(bootcampId,
            "Tech Elevator",
            "Tech Elevator offers a coding bootcamp dedicated to readying students.",
            "Av. Melchor Perez #123",
            LocalDate.of(2021, 3, 12),
            LocalDate.of(2021, 7, 15), 1, 1L, 1, 1L,
            30,
            new HashSet<>(), 1, 1L, Collections.emptySet());
        BootcampDTO bootcampEditionDTO = new BootcampDTO(bootcamp);

        when(bootcampRepository.findById(bootcampId)).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(
            EntityNotFoundException.class,
            () -> bootcampHandleUseCase.edit(bootcampId, bootcampEditionDTO));
        assertSame(ErrorsUtil.ERROR_MESSAGE_ENTITY_NOT_FOUND, exception.getErrorMessage());
    }
}