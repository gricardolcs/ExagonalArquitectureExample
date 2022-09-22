package com.jalasoft.bootcamp.bootcamp.infrastructure.dto.status;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jalasoft.bootcamp.bootcamp.domain.status.Status;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class StatusDTO
{
    @ApiModelProperty(
        example = "-8164615319058060193"
    )
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    private final long id;

    @ApiModelProperty(
        example = "PASSED"
    )
    @JsonProperty("name")
    private final String name;

    public StatusDTO(Status status)
    {
        this.id = status.getId().getId();
        this.name = status.getName().getName();
    }
}
