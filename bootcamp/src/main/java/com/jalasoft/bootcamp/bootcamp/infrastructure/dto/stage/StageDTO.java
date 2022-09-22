package com.jalasoft.bootcamp.bootcamp.infrastructure.dto.stage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jalasoft.bootcamp.bootcamp.domain.stage.Stage;
import com.jalasoft.bootcamp.bootcamp.domain.stage.schema.InputField;
import com.jalasoft.bootcamp.bootcamp.domain.stage.schema.InputFieldSchema;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class StageDTO
{
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    private final long id;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("order")
    private final int order;

    @JsonProperty("showComments")
    private final boolean showComments;

    @JsonProperty("schema")
    private final List<InputFieldSchema> schemas;

    public StageDTO(Stage stage)
    {
        this.id = stage.getId();
        this.name = stage.getStageName().getName();
        this.order = stage.getStageOrder().getOrder();
        this.showComments = stage.showComments();
        this.schemas = stage.getSchemas().stream()
            .map(InputField::getInputFieldSchema).collect(Collectors.toList());
    }
}
