package com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.department.fetch;

import com.jalasoft.bootcamp.becommon.domain.ddd.IsUseCase;
import com.jalasoft.bootcamp.bootcamp.domain.department.Department;
import com.jalasoft.bootcamp.bootcamp.domain.department.DepartmentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@IsUseCase
@Service
public class DepartmentFetchAllUseCase
{
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentFetchAllUseCase(final DepartmentRepository departmentRepository)
    {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> fetchAll()
    {
        return departmentRepository.findAll();
    }
}
