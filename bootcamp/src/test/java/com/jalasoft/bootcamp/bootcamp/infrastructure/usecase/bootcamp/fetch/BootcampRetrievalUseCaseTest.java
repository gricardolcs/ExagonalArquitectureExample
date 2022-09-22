package com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.bootcamp.fetch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.Bootcamp;
import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.BootcampRepository;
import com.jalasoft.bootcamp.bootcamp.domain.department.Department;
import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.bootcamp.domain.projecttype.ProjectType;
import com.jalasoft.bootcamp.bootcamp.domain.projecttype.ProjectTypeName;
import com.jalasoft.bootcamp.bootcamp.domain.workflow.Workflow;
import com.jalasoft.bootcamp.bootcamp.domain.workflow.WorkflowName;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.bootcamp.BootcampDTO;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class BootcampRetrievalUseCaseTest
{
    @Mock
    private BootcampRepository bootcampRepository;

    @Mock
    private BootcampDTOBuilder bootcampDTOBuilder;

    @InjectMocks
    private BootcampFetchUseCase bootcampFetchUseCase;

    private Bootcamp bootcamp;
    private long bootcampId;
    private long departmentId;
    private long projectTypeId;
    private long workflowId;

    @BeforeEach
    void setUp()
    {
        workflowId = 1L;
        departmentId = 1L;
        projectTypeId = 1L;
        bootcampId = UUID.randomUUID().getLeastSignificantBits();
        MockitoAnnotations.initMocks(this);
        String name = "Bolivian Bootcamp";
        String description = "First Bolivian Bootcamp";
        LocalDate startDate = LocalDate.of(2021, Month.JUNE, 20);
        LocalDate endDate = LocalDate.of(2021, Month.DECEMBER, 20);
        int workflowType = 1;
        int department = 1;
        int participantsExpectedQuantity = 30;
        int projectType = 1;
        bootcamp = new Bootcamp(bootcampId, name,
            description,
            "Av. Melchor Perez #123", startDate,
            endDate, workflowType, workflowId, department, departmentId,
            participantsExpectedQuantity, new HashSet<>(),
            projectType, projectTypeId, Collections.emptySet());
    }

    @Test
    void testFindBootcampById()
    {
        BootcampDTO expectedResult = new BootcampDTO(bootcamp);

        Workflow workflow = new Workflow(workflowId, new WorkflowName("Dev Workflow"),
            Collections.emptyList());
        Department department = new Department(departmentId, "QA", "This is the description de QA department");
        ProjectType projectType = new ProjectType(projectTypeId, new ProjectTypeName("Bootcamp"));
        expectedResult.setWorkflow(new WorkflowDTO(workflow))
            .setDepartment(new DepartmentDTO(department))
            .setProject(new ProjectTypeDTO(projectType));

        Optional<Bootcamp> optionalEntityType = Optional.of(bootcamp);
        Mockito.when(bootcampRepository.findById(bootcampId)).thenReturn(optionalEntityType);
        Mockito.when(bootcampDTOBuilder.build(bootcamp)).thenReturn(expectedResult);
        BootcampDTO actualResult = bootcampFetchUseCase.findById(bootcampId);

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
    public void testNotFound()
    {
        Optional<Bootcamp> optionalEntityType = Optional.of(bootcamp);
        Mockito.when(bootcampRepository.findById(bootcampId)).thenReturn(optionalEntityType);

        EntityNotFoundException exception = assertThrows(
            EntityNotFoundException.class,
            () -> bootcampFetchUseCase.findById(1));
        assertSame(ErrorsUtil.ERROR_MESSAGE_ENTITY_NOT_FOUND, exception.getErrorMessage());
    }
}
