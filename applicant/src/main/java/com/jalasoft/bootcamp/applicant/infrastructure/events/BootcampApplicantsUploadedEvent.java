package com.jalasoft.bootcamp.applicant.infrastructure.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BootcampApplicantsUploadedEvent
{
    @JsonProperty("bootcampId")
    private final long bootcampId;

    @JsonProperty("applicantIds")
    private final Set<Long> applicantIds;

    @Override
    public String toString()
    {
        return "BootcampApplicantsUploadedEvent{" +
            "bootcampId=" + bootcampId +
            ", applicantIds=" + applicantIds +
            '}';
    }
}
