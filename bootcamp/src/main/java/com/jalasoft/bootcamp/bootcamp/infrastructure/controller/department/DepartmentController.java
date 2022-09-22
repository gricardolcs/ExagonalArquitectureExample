package com.jalasoft.bootcamp.bootcamp.infrastructure.controller.department;

import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.department.DepartmentDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.department.manipulation.DepartmentHandleUseCase;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.department.fetch.DepartmentFetchAllUseCase;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan(basePackages = "com.jalasoft.bootcamp.becommon.domain.exceptions")
@RestController
@RequestMapping("/department")
public class DepartmentController
{
    private final DepartmentFetchAllUseCase departmentFetchAllUseCase;
    private final DepartmentHandleUseCase departmentCreateUseCase;

    @Autowired
    public DepartmentController(
        final DepartmentFetchAllUseCase departmentFetchAllUseCase,
        final DepartmentHandleUseCase departmentCreateUseCase)
    {
        this.departmentFetchAllUseCase = departmentFetchAllUseCase;
        this.departmentCreateUseCase = departmentCreateUseCase;
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@Valid @RequestBody final DepartmentDTO departmentDTO)
    {
        return new ResponseEntity<>(
            departmentCreateUseCase.create(departmentDTO),
            HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> fetchAllDepartments()
    {
        return ResponseEntity.ok(
            departmentFetchAllUseCase.fetchAll().stream()
                .map(DepartmentDTO::new)
                .collect(Collectors.toList()));
    }
}
