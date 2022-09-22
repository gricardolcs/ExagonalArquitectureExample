package com.jalasoft.bootcamp.bootcamp.infrastructure.dto.workflow;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jalasoft.bootcamp.bootcamp.domain.workflow.Workflow;
import io.swagger.annotations.ApiModelProperty;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class WorkflowStagesDTO
{
    @ApiModelProperty(
        example = "-3198164615058060193"
    )
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    private final long id;

    @ApiModelProperty(
        example = "Development workflow"
    )
    @JsonProperty("name")
    private final String name;

    @ApiModelProperty(
        example = "[IN_PROGRESS, PASSED, FAILED, WITHDRAW]"
    )
    @JsonProperty("stages")
    private final List<String> stagesName;

    public WorkflowStagesDTO(Workflow workflow)
    {
        this.id = workflow.getId();
        this.name = workflow.getName().getName();
        this.stagesName = workflow.getStages().stream()
            .sorted(Comparator.comparingInt(stage -> stage.getStageOrder().getOrder()))
            .map(stage -> stage.getStageName().getName())
            .collect(Collectors.toList());
    }
}
