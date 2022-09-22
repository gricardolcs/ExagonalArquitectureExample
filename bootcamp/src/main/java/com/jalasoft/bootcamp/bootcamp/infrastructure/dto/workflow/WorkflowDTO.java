package com.jalasoft.bootcamp.bootcamp.infrastructure.dto.workflow;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jalasoft.bootcamp.bootcamp.domain.workflow.Workflow;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.stage.StageDTO;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class WorkflowDTO
{
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    private final long id;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("stages")
    private final List<StageDTO> stages;

    public WorkflowDTO(Workflow workflow)
    {
        this.id = workflow.getId();
        this.name = workflow.getName().getName();
        this.stages = workflow.getStages().stream()
            .map(StageDTO::new)
            .sorted(Comparator.comparingInt(StageDTO::getOrder))
            .collect(Collectors.toList());
    }
}
