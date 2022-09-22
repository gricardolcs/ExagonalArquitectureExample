package com.jalasoft.bootcamp.bootcamp.infrastructure.dto.bootcamp;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.Bootcamp;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.department.DepartmentDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.projecttype.ProjectTypeDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.workflow.WorkflowDTO;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class BootcampDTO
{
    @ApiModelProperty(
        example = "-5319461058060181693"
    )
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonProperty("id")
    private long id;

    @ApiModelProperty(
        example = "Bolivian Bootcamp"
    )
    @JsonProperty("name")
    private final String name;

    @ApiModelProperty(
        example = "First Bolivian Bootcamp"
    )
    @JsonProperty("description")
    private final String description;

    @ApiModelProperty(
        example = "Av. Melchor Perez #123"
    )
    @JsonProperty("location")
    private final String location;

    @ApiModelProperty(
        example = "2021-03-11",
        required = true
    )
    @JsonSerialize(as = LocalDate.class)
    private final LocalDate startDate;

    @ApiModelProperty(
        example = "2021-12-12",
        required = true
    )
    @JsonSerialize(as = LocalDate.class)
    private final LocalDate endDate;

    @ApiModelProperty(
        example = "1",
        required = true,
        value = "This is the basic value for workflow type, forward it will change"
    )
    @JsonProperty("workflowType")
    private final int workflowType;

    @ApiModelProperty(
        example = "1",
        required = true,
        value = "This is the basic value for department, forward it will change"
    )
    @JsonProperty("departmentType")
    private final int departmentType;

    @ApiModelProperty(
        example = "1",
        required = true,
        value = "This is the basic value for project type, forward it will change"
    )
    @JsonProperty("projectType")
    private final int projectType;

    @ApiModelProperty(
        hidden = true
    )
    @JsonProperty("workflow")
    private WorkflowDTO workflow;

    @ApiModelProperty(
        hidden = true
    )
    @JsonProperty("department")
    private DepartmentDTO department;

    @JsonProperty("participantsExpectedQuantity")
    private final int participantsExpectedQuantity;

    @JsonProperty("acceptedParticipants")
    private final int acceptedParticipants;

    @JsonProperty("actualParticipants")
    private final int actualParticipants;

    @ApiModelProperty(
        hidden = true
    )
    @JsonProperty("project")
    private ProjectTypeDTO project;

    public BootcampDTO(final Bootcamp bootcamp)
    {
        this.id = bootcamp.getId();
        this.name = bootcamp.getName();
        this.description = bootcamp.getDescription();
        this.location = bootcamp.getLocation();
        this.startDate = bootcamp.getBootcampStartDate();
        this.endDate = bootcamp.getBootcampEndDate();
        this.workflowType = bootcamp.getWorkflowType();
        this.departmentType = bootcamp.getDepartment();
        this.participantsExpectedQuantity = bootcamp.getBootcampParticipantsExpectedQuantity();
        this.acceptedParticipants = bootcamp.getAcceptedParticipants().size();
        this.actualParticipants = bootcamp.getBootcampMembers().size();
        this.projectType = bootcamp.getProjectType();
    }

    @JsonCreator
    public BootcampDTO(
        String name, String description, String location, LocalDate startDate,
        LocalDate endDate, int workflowType, int departmentType, int participantsExpectedQuantity,
        int acceptedParticipants, int actualParticipants, int projectType)
    {
        this.name = name;
        this.description = description;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.workflowType = workflowType;
        this.departmentType = departmentType;
        this.participantsExpectedQuantity = participantsExpectedQuantity;
        this.acceptedParticipants = acceptedParticipants;
        this.actualParticipants = actualParticipants;
        this.projectType = projectType;
    }

    public static BootcampDTO convert(final Bootcamp bootcamp)
    {
        return new BootcampDTO(bootcamp);
    }

    public BootcampDTO setWorkflow(WorkflowDTO workflow)
    {
        this.workflow = workflow;
        return this;
    }

    public BootcampDTO setDepartment(DepartmentDTO department)
    {
        this.department = department;
        return this;
    }

    public BootcampDTO setProject(ProjectTypeDTO project)
    {
        this.project = project;
        return this;
    }
}
