package com.jalasoft.bootcamp.bootcamp.infrastructure.utils;

import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.Bootcamp;
import com.jalasoft.bootcamp.bootcamp.domain.department.Department;
import com.jalasoft.bootcamp.bootcamp.domain.department.DepartmentRepository;
import com.jalasoft.bootcamp.bootcamp.domain.projecttype.ProjectType;
import com.jalasoft.bootcamp.bootcamp.domain.projecttype.ProjectTypeRepository;
import com.jalasoft.bootcamp.bootcamp.domain.workflow.Workflow;
import com.jalasoft.bootcamp.bootcamp.domain.workflow.WorkflowRepository;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.bootcamp.BootcampDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.department.DepartmentDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.projecttype.ProjectTypeDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.workflow.WorkflowDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BootcampDTOBuilder
{
    private final WorkflowRepository workflowRepository;
    private final DepartmentRepository departmentRepository;
    private final ProjectTypeRepository projectTypeRepository;

    @Autowired
    public BootcampDTOBuilder(
        WorkflowRepository workflowRepository,
        DepartmentRepository departmentRepository,
        ProjectTypeRepository projectTypeRepository)
    {
        this.workflowRepository = workflowRepository;
        this.departmentRepository = departmentRepository;
        this.projectTypeRepository = projectTypeRepository;
    }

    public BootcampDTO build(final Bootcamp bootcamp)
    {

        Workflow workflow = workflowRepository
            .findById(bootcamp.getWorkflowId()).get();

        Department department = departmentRepository
            .findById(bootcamp.getDepartmentId()).get();

        ProjectType projectType = projectTypeRepository
            .findById(bootcamp.getProjectTypeId()).get();

        WorkflowDTO workflowDTO = new WorkflowDTO(workflow);
        DepartmentDTO departmentDTO = new DepartmentDTO(department);
        ProjectTypeDTO projectTypeDTO = new ProjectTypeDTO(projectType);

        return new BootcampDTO(bootcamp)
            .setWorkflow(workflowDTO)
            .setDepartment(departmentDTO)
            .setProject(projectTypeDTO);
    }
}
