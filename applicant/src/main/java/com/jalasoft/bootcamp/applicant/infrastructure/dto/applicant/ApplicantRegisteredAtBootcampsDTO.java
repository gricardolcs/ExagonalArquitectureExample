package com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ApplicantRegisteredAtBootcampsDTO
{
    @ApiModelProperty(
        example = "['-6641241093137187368', '-9313718736641241068']"
    )
    @JsonProperty("applicantRegisteredAtBootcamps")
    private final Set<String> applicantRegisteredAtBootcamps;

    public ApplicantRegisteredAtBootcampsDTO(Set<Long> registeredBootcamps)
    {
        this.applicantRegisteredAtBootcamps = registeredBootcamps.stream().map(String::valueOf).collect(
            Collectors.toSet());
    }

    public Set<String> getApplicantRegisteredAtBootcamps()
    {
        return applicantRegisteredAtBootcamps;
    }
}
