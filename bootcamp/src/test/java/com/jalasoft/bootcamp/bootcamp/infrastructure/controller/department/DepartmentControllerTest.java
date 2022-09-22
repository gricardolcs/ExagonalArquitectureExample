package com.jalasoft.bootcamp.bootcamp.infrastructure.controller.department;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jalasoft.bootcamp.bootcamp.domain.department.Department;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.department.DepartmentDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.department.manipulation.DepartmentHandleUseCase;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class DepartmentControllerTest
{
    @Mock
    private DepartmentHandleUseCase departmentHandleUseCase;
    @InjectMocks
    private DepartmentController departmentController;

    private long id;
    private String name;
    private String description;
    private Department department;

    @Test
    void testCreateDepartment()
    {
        id = UUID.randomUUID().getLeastSignificantBits();
        name = "Development";
        description = "This is the Development description";
        department = new Department(id, name, description);
        DepartmentDTO departmentToCreateDTO = new DepartmentDTO(name, description);
        DepartmentDTO departmentDTO = new DepartmentDTO(department);

        Mockito.when(departmentHandleUseCase.create(departmentToCreateDTO))
            .thenReturn(departmentDTO);

        ResponseEntity<DepartmentDTO> departmentCreated =
            departmentController.createDepartment(departmentToCreateDTO);
        assertEquals(HttpStatus.CREATED, departmentCreated.getStatusCode());
        assertEquals(departmentDTO.getId(), departmentCreated.getBody().getId());
        assertEquals(departmentDTO.getName(), departmentCreated.getBody().getName());
    }
}
