package com.jalasoft.bootcamp.becommon.domain.exceptions;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseException extends RuntimeException
{
    private String errorMessage;
    private int statusCode;
    private List<Errors> details;
}
