package com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class ApplicantRegisteredInBootcampDTO
{
    @ApiModelProperty(
        example = "['-6641241093137187368', '-9313718736641241068']"
    )
    @JsonProperty("registeredApplicants")
    private final Set<String> registeredApplicants;

    public ApplicantRegisteredInBootcampDTO(Set<Long> registeredApplicants)
    {
        this.registeredApplicants = registeredApplicants.stream().map(String::valueOf)
            .collect(Collectors.toSet());
    }
}
