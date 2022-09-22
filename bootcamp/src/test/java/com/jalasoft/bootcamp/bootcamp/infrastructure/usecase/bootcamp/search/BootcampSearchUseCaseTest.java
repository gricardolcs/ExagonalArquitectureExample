package com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.bootcamp.search;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.Bootcamp;
import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.BootcampRepository;
import com.jalasoft.bootcamp.bootcamp.domain.department.Department;
import com.jalasoft.bootcamp.bootcamp.domain.projecttype.ProjectType;
import com.jalasoft.bootcamp.bootcamp.domain.projecttype.ProjectTypeName;
import com.jalasoft.bootcamp.bootcamp.domain.workflow.Workflow;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.bootcamp.fetch.BootcampFetchUseCase;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.UUID;
import java.util.List;
import java.util.Arrays;
import java.util.HashSet;

import com.jalasoft.bootcamp.bootcamp.domain.workflow.WorkflowName;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.bootcamp.BootcampDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.department.DepartmentDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.projecttype.ProjectTypeDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.workflow.WorkflowDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.utils.BootcampDTOBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class BootcampSearchUseCaseTest
{
    @Mock
    private BootcampRepository bootcampRepository;

    @Mock
    private BootcampDTOBuilder bootcampDTOBuilder;

    @InjectMocks
    private BootcampFetchUseCase bootcampFetchUseCase;

    private Bootcamp bootcamp;
    private long bootcampId;
    private long workflowId;
    private long departmentId;
    private long projectTypeId;
    private String bootcampName;

    @BeforeEach
    void setUp()
    {
        bootcampId = UUID.randomUUID().getLeastSignificantBits();
        workflowId = 1L;
        departmentId = 1L;
        projectTypeId = 1L;
        MockitoAnnotations.initMocks(this);
        String name = "Bolivian Bootcamp";
        String description = "First Bolivian Bootcamp";
        LocalDate startDate = LocalDate.of(2021, Month.JUNE, 20);
        LocalDate endDate = LocalDate.of(2021, Month.DECEMBER, 20);
        int workflowType = 1;
        int department = 1;
        int participantsExpectedQuantity = 30;
        int projectType = 1;
        bootcampName = name;
        bootcamp = new Bootcamp(bootcampId, bootcampName, description,
            "Av. Melchor Perez #123", startDate,
            endDate, workflowType, workflowId, department, departmentId,
            participantsExpectedQuantity, new HashSet<>(),
            projectType, projectTypeId, Collections.emptySet());
    }

    @Test
    void testSearchBootcamp()
    {
        BootcampDTO bootcampDTO = new BootcampDTO(bootcamp);
        Workflow workflow = new Workflow(workflowId, new WorkflowName("Dev Workflow"),
            Collections.emptyList());
        Department department = new Department(departmentId, "QA", "This is the description de QA department");
        ProjectType projectType = new ProjectType(projectTypeId, new ProjectTypeName("Bootcamp"));
        bootcampDTO.setWorkflow(new WorkflowDTO(workflow))
            .setDepartment(new DepartmentDTO(department))
            .setProject(new ProjectTypeDTO(projectType));
        List<BootcampDTO> expectedResult = Arrays.asList(bootcampDTO);

        Mockito.when(bootcampRepository.findByName(bootcampName))
            .thenReturn(Arrays.asList(bootcamp));
        Mockito.when(bootcampDTOBuilder.build(bootcamp))
            .thenReturn(bootcampDTO);

        List<BootcampDTO> actualResult = bootcampFetchUseCase.findBootcampByName("Bolivian "
            + "Bootcamp");

        assertEquals(expectedResult.size(), actualResult.size());
        assertEquals(expectedResult.get(0).getId(), actualResult.get(0).getId());
        assertEquals(expectedResult.get(0).getName(), actualResult.get(0).getName());
        assertEquals(expectedResult.get(0).getDescription(), actualResult.get(0).getDescription());
        assertEquals(expectedResult.get(0).getLocation(), actualResult.get(0).getLocation());
        assertEquals(expectedResult.get(0).getStartDate(), actualResult.get(0).getStartDate());
        assertEquals(expectedResult.get(0).getEndDate(), actualResult.get(0).getEndDate());
        assertEquals(
            expectedResult.get(0).getWorkflowType(),
            actualResult.get(0).getWorkflowType());
        assertEquals(
            expectedResult.get(0).getDepartmentType(),
            actualResult.get(0).getDepartmentType());
        assertEquals(expectedResult.get(0).getProjectType(), actualResult.get(0).getProjectType());
        assertEquals(expectedResult.get(0).getWorkflow(), actualResult.get(0).getWorkflow());
        assertEquals(expectedResult.get(0).getDepartment(), actualResult.get(0).getDepartment());
        assertEquals(expectedResult.get(0).getProject(), actualResult.get(0).getProject());
    }

    @Test
    void testSearchBootcampNotExist()
    {
        Mockito.when(bootcampRepository.findByName(bootcampName))
            .thenReturn(Collections.emptyList());
        List<BootcampDTO> actualResult = bootcampFetchUseCase.findBootcampByName("Bolivian "
            + "Bootcamp");

        assertEquals(0, actualResult.size());
    }
}
