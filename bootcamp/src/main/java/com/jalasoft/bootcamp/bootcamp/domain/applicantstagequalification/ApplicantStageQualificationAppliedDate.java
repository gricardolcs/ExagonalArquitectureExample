package com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification;

import com.jalasoft.bootcamp.becommon.domain.ddd.IsValueObject;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@IsValueObject
@Embeddable
public class ApplicantStageQualificationAppliedDate
{
    @Column
    private LocalDate appliedDate;

    /*
     * Used only by JPA.
     */
    protected ApplicantStageQualificationAppliedDate()
    {
        // JPA usage only.
    }

    /*
     * Used only by JPA.
     */
    private void setAppliedDate(LocalDate appliedDate)
    {
        this.appliedDate = appliedDate;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof ApplicantStageQualificationAppliedDate))
        {
            return false;
        }
        ApplicantStageQualificationAppliedDate that = (ApplicantStageQualificationAppliedDate) o;
        return Objects.equals(getAppliedDate(), that.getAppliedDate());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getAppliedDate());
    }
}
