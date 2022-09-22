package com.jalasoft.bootcamp.applicant.domain.applicant;

import com.jalasoft.bootcamp.becommon.domain.ddd.IsIdentifiable;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@IsIdentifiable
public class Report implements Serializable
{
    private final Long bootcampId;
    private final String fileName;
    private byte[] report;
}
