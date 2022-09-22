package com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.upload;

import com.jalasoft.bootcamp.applicant.domain.applicant.ComplementaryAttribute;

import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class ApplicantBootcampHistoryCreation
{
    private final long bootcampId;
    private final long applicantId;
    private final List<ComplementaryAttribute> complementaryAttributes;

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
        ApplicantBootcampHistoryCreation that = (ApplicantBootcampHistoryCreation) o;
        return bootcampId == that.bootcampId && applicantId == that.applicantId &&
            Objects.equals(complementaryAttributes, that.complementaryAttributes);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(bootcampId, applicantId, complementaryAttributes);
    }
}
