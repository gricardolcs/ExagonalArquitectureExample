package com.jalasoft.bootcamp.bootcamp.infrastructure.events;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApplicantStageQualifiedEventList
{
    private List<ApplicantStageQualifiedEvent> applicantStageQualifiedEvents;
}
