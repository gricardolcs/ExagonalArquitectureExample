package com.jalasoft.bootcamp.setting.infrastructure.events.eventDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
public class SkillsEvent
{
    @JsonProperty("skills")
    private List<String> skills;
}
