package com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Getter
public class ApplicantUploadDTO
{
    @ApiModelProperty(
        example = "['-12345678909123', '-19897654332121', '-90127834675689']"
    )
    private final List<String> applicantsIds;

    @ApiModelProperty(
        example = "There are applicants that couldn't be loaded, please review them"
    )
    private final String failedMessage;

    @ApiModelProperty(
        example = "[1, 2, 3]"
    )
    private final List<Integer> failedRows;

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        ApplicantUploadDTO that = (ApplicantUploadDTO) o;
        return Objects.equals(applicantsIds, that.applicantsIds)
            && Objects.equals(failedMessage, that.failedMessage) && Objects.equals(
            failedRows,
            that.failedRows);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(applicantsIds, failedMessage, failedRows);
    }
}
