package com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.bootcamp.creation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.Bootcamp;
import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.BootcampRepository;
import com.jalasoft.bootcamp.bootcamp.domain.department.Department;
import com.jalasoft.bootcamp.bootcamp.domain.projecttype.ProjectType;
import com.jalasoft.bootcamp.bootcamp.domain.projecttype.ProjectTypeName;
import com.jalasoft.bootcamp.bootcamp.domain.workflow.Workflow;
import com.jalasoft.bootcamp.bootcamp.domain.workflow.WorkflowName;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.bootcamp.BootcampDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.department.DepartmentDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.projecttype.ProjectTypeDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.workflow.WorkflowDTO;
import com.jalasoft.bootcamp.becommon.infrastructure.factory.Factory;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;
import com.jalasoft.bootcamp.bootcamp.infrastructure.utils.BootcampDTOBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class BootcampCreationUseCaseTest
{
    @Mock
    private BootcampRepository bootcampRepository;

    @Mock
    private Factory<Bootcamp, BootcampDTO> bootcampFactory;

    @Mock
    private BootcampDTOBuilder bootcampDTOBuilder;

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
    private int acceptedParticipants;
    private int actualParticipants;
    private int projectType;
    private long projectTypeId;
    private Bootcamp bootcamp;

    @BeforeEach
    void setUp()
    {
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
        acceptedParticipants = 1;
        actualParticipants = 1;
        projectType = 1;
        projectTypeId = 1L;
        bootcamp = new Bootcamp(UUID.randomUUID().getLeastSignificantBits(), name,
            description, location,
            startDate, endDate, workflowType, workflowId,
            department, departmentId,
            participantsExpectedQuantity,
            new HashSet<>(), projectType, projectTypeId, Collections.emptySet());
    }

    @Test
    void testCreateBootcamp()
    {
        BootcampDTO bootcampCreation = new BootcampDTO(name, description, location,
            startDate, endDate, workflowType, department, participantsExpectedQuantity,
            acceptedParticipants, actualParticipants, projectType);
        BootcampDTO expectedResult = new BootcampDTO(bootcamp);
        Workflow workflow = new Workflow(workflowId, new WorkflowName("Dev Workflow"),
            Collections.emptyList());
        Department department = new Department(departmentId, "QA", "This is "
            + "the description de QA department");
        ProjectType projectType = new ProjectType(projectTypeId, new ProjectTypeName("Bootcamp"));
        expectedResult.setWorkflow(new WorkflowDTO(workflow))
            .setDepartment(new DepartmentDTO(department))
            .setProject(new ProjectTypeDTO(projectType));

        lenient().when(bootcampFactory.create(bootcampCreation))
            .thenReturn(bootcamp);
        lenient().when(bootcampRepository.save(bootcamp))
            .thenReturn(bootcamp);
        Mockito.when(bootcampDTOBuilder.build(bootcamp))
            .thenReturn(expectedResult);
        BootcampDTO bootcampSaved = bootcampDTOBuilder.build(bootcamp);

        assertEquals(expectedResult.getId(), bootcampSaved.getId());
        assertEquals(expectedResult.getName(), bootcampSaved.getName());
        assertEquals(expectedResult.getDescription(), bootcampSaved.getDescription());
        assertEquals(expectedResult.getStartDate(), bootcampSaved.getStartDate());
        assertEquals(expectedResult.getEndDate(), bootcampSaved.getEndDate());
        assertEquals(expectedResult.getWorkflowType(), bootcampSaved.getWorkflowType());
        assertEquals(expectedResult.getDepartmentType(), bootcampSaved.getDepartmentType());
        assertEquals(expectedResult.getProjectType(), bootcampSaved.getProjectType());
        assertEquals(expectedResult.getWorkflow(), bootcampSaved.getWorkflow());
        assertEquals(expectedResult.getDepartment(), bootcampSaved.getDepartment());
        assertEquals(expectedResult.getProject(), bootcampSaved.getProject());
    }
}
