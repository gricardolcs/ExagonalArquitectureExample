package com.jalasoft.bootcamp.applicant.domain.applicant;

import com.jalasoft.bootcamp.becommon.domain.ddd.IsValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@IsValueObject
public class CurriculumVitae
{
    private final byte[] url;
    private final String fileName;
    private final String content;
}
