package com.jalasoft.bootcamp.bootcamp.infrastructure.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BootcampApplicantAssociationEvent
{
    @JsonProperty(value = "bootcampId")
    private long bootcampId;

    @JsonProperty(value = "applicantIds")
    private Set<Long> applicantIds;
}
