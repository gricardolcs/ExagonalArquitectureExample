package com.jalasoft.bootcamp.bootcamp.infrastructure.controller.bootcamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.Bootcamp;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.bootcamp.BootcampDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.bootcamp.fetch.BootcampFetchUseCase;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class BootcampPaginationControllerTest
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
    public void setUp()
    {
        id = UUID.randomUUID().getLeastSignificantBits();
        name = "Colombian Bootcamp";
        description = "First Colombian Bootcamp";
        location = "Latam";
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
    public void testBootcampPaginationSuccessfully()
    {
        Bootcamp bootcamp = new Bootcamp(id, name,
            description, location,
            startDate, endDate, workflowType, workflowId,
            department, departmentId,
            participantsExpectedQuantity,
            new HashSet<>(), projectType, projectTypeId, Collections.emptySet());
        Page<BootcampDTO> page = new PageImpl(List.of(new BootcampDTO(bootcamp)));
        PageRequest pageable = PageRequest.of(1, 20);

        Mockito.when(bootcampFetchUseCase.fetch(pageable)).thenReturn(page);
        ResponseEntity<Page<BootcampDTO>> actualResult =
            bootcampController.fetch(pageable);
        Page<BootcampDTO> actualResultBody = actualResult.getBody();

        assertEquals(200, actualResult.getStatusCodeValue());
        assertEquals("Colombian Bootcamp", actualResultBody.getContent().get(0).getName());
        assertEquals(
            "First Colombian Bootcamp",
            actualResultBody.getContent().get(0).getDescription());
        assertEquals("Latam", actualResultBody.getContent().get(0).getLocation());
        assertEquals(1, actualResultBody.getContent().get(0).getWorkflowType());
        assertEquals(1, actualResultBody.getContent().get(0).getDepartmentType());
        assertEquals(1, actualResultBody.getContent().get(0).getProjectType());
        assertEquals(startDate, actualResultBody.getContent().get(0).getStartDate());
        assertEquals(endDate, actualResultBody.getContent().get(0).getEndDate());
    }

    @Test
    public void testBootcampPaginationEmpty()
    {
        Page<BootcampDTO> page = new PageImpl(Lists.emptyList());
        PageRequest pageable = PageRequest.of(1, 20);
        Mockito.when(bootcampFetchUseCase.fetch(pageable)).thenReturn(page);

        ResponseEntity<Page<BootcampDTO>> actualResult =
            bootcampController.fetch(pageable);

        assertEquals(200, actualResult.getStatusCodeValue());
        assertEquals(Collections.emptyList(), actualResult.getBody().getContent());
    }
}
