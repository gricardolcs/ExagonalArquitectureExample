package com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.bootcamp.fetch;

import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.Bootcamp;
import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.BootcampRepository;
import com.jalasoft.bootcamp.bootcamp.domain.department.Department;
import com.jalasoft.bootcamp.bootcamp.domain.projecttype.ProjectType;
import com.jalasoft.bootcamp.bootcamp.domain.projecttype.ProjectTypeName;
import com.jalasoft.bootcamp.bootcamp.domain.workflow.Workflow;

import java.util.HashSet;

import com.jalasoft.bootcamp.bootcamp.domain.workflow.WorkflowName;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.bootcamp.BootcampDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.department.DepartmentDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.projecttype.ProjectTypeDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.workflow.WorkflowDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.utils.BootcampDTOBuilder;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.Month;
import java.util.Collections;
import java.util.UUID;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BootcampPaginationUseCaseTest
{
    @Mock
    private BootcampRepository bootcampRepository;

    @Mock
    private BootcampDTOBuilder bootcampDTOBuilder;

    @InjectMocks
    private BootcampFetchUseCase bootcampFetchUseCase;

    private long bootcampId;
    private long workflowId;
    private long departmentId;
    private long projectTypeId;
    private Bootcamp bootcamp;

    @BeforeEach
    public void setUp()
    {
        workflowId = 1L;
        departmentId = 1L;
        projectTypeId = 1L;
        bootcampId = UUID.randomUUID().getLeastSignificantBits();
        MockitoAnnotations.initMocks(this);
        String name = "Colombian Bootcamp";
        String description = "First Colombian Bootcamp";
        String location = "Latam";
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
    public void testBootcampPaginationSuccessfully()
    {
        Page<Bootcamp> page = new PageImpl(List.of(bootcamp));
        PageRequest pageable = PageRequest.of(0, 1);
        BootcampDTO bootcampDTO = new BootcampDTO(bootcamp);
        Workflow workflow = new Workflow(workflowId, new WorkflowName("Dev Workflow"),
            Collections.emptyList());
        Department department = new Department(departmentId, "QA", "This is the description de QA department");
        ProjectType projectType = new ProjectType(projectTypeId, new ProjectTypeName("Bootcamp"));
        bootcampDTO.setWorkflow(new WorkflowDTO(workflow))
            .setDepartment(new DepartmentDTO(department))
            .setProject(new ProjectTypeDTO(projectType));
        Page<BootcampDTO> expectedResult = new PageImpl(List.of(bootcampDTO));

        Mockito.when(bootcampRepository.findAll(pageable)).thenReturn(page);
        Mockito.when(bootcampDTOBuilder.build(bootcamp)).thenReturn(bootcampDTO);
        Page<BootcampDTO> actualResult = bootcampFetchUseCase.fetch(pageable);

        assertEquals(
            expectedResult.getContent().get(0).getId(),
            actualResult.getContent().get(0).getId());
        assertEquals(
            expectedResult.getContent().get(0).getName(),
            actualResult.getContent().get(0).getName());
        assertEquals(
            expectedResult.getContent().get(0).getDescription(),
            actualResult.getContent().get(0).getDescription());
        assertEquals(
            expectedResult.getContent().get(0).getLocation(),
            actualResult.getContent().get(0).getLocation());
        assertEquals(
            expectedResult.getContent().get(0).getStartDate(),
            actualResult.getContent().get(0).getStartDate());
        assertEquals(
            expectedResult.getContent().get(0).getEndDate(),
            actualResult.getContent().get(0).getEndDate());
        assertEquals(
            expectedResult.getContent().get(0).getWorkflowType(),
            actualResult.getContent().get(0).getWorkflowType());
        assertEquals(
            expectedResult.getContent().get(0).getDepartmentType(),
            actualResult.getContent().get(0).getDepartmentType());
        assertEquals(
            expectedResult.getContent().get(0).getProjectType(),
            actualResult.getContent().get(0).getProjectType());
        assertEquals(
            expectedResult.getContent().get(0).getWorkflow(),
            actualResult.getContent().get(0).getWorkflow());
        assertEquals(
            expectedResult.getContent().get(0).getDepartment(),
            actualResult.getContent().get(0).getDepartment());
        assertEquals(
            expectedResult.getContent().get(0).getProject(),
            actualResult.getContent().get(0).getProject());
    }

    @Test
    public void testBootcampPaginationEmptyList()
    {
        PageRequest pageable = PageRequest.of(1, 20);
        Page<Bootcamp> page = new PageImpl(Lists.emptyList());

        Mockito.when(bootcampRepository.findAll(pageable)).thenReturn(page);
        Page<BootcampDTO> actualResult = bootcampFetchUseCase.fetch(pageable);

        assertEquals(Collections.emptyList(), actualResult.getContent());
    }
}
