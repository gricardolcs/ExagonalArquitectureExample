package com.jalasoft.bootcamp.applicant.domain.applicant;

import com.jalasoft.bootcamp.becommon.domain.ddd.IsValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@IsValueObject
public class Location
{
    private final String country;
    private final String city;
}
