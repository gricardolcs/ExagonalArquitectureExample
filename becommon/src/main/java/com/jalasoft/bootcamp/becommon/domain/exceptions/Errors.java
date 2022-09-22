package com.jalasoft.bootcamp.becommon.domain.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Errors
{
    @JsonInclude(Include.NON_NULL)
    private String field;

    @JsonInclude(Include.NON_NULL)
    private String error;
}
