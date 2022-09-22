package com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.department.manipulation;

import com.jalasoft.bootcamp.becommon.domain.ddd.IsUseCase;
import com.jalasoft.bootcamp.becommon.domain.exceptions.DuplicatedEntityException;
import com.jalasoft.bootcamp.becommon.infrastructure.factory.Factory;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.Field;
import com.jalasoft.bootcamp.bootcamp.domain.department.Department;
import com.jalasoft.bootcamp.bootcamp.domain.department.DepartmentRepository;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.department.DepartmentDTO;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@IsUseCase
@Service
public class DepartmentHandleUseCase
{
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentHandleUseCase(
        final DepartmentRepository departmentRepository)
    {
        this.departmentRepository = departmentRepository;
    }

    public DepartmentDTO create(final DepartmentDTO departmentDTO)
    {
        Department department = new Department(
            UUID.randomUUID().getLeastSignificantBits(), departmentDTO.getName(),
            departmentDTO.getDescription());
        try
        {
            Department departmentSaved = departmentRepository.save(department);
            return new DepartmentDTO(departmentSaved);
        }
        catch (Exception exception)
        {
            throw new DuplicatedEntityException(
                Field.NAME,
                ErrorsUtil.getDescription(ErrorsUtil.ERROR_DESCRIPTION_DEPARTMENT_DUPLICATED,
                    Field.NAME, department.getName()));
        }
    }
}
