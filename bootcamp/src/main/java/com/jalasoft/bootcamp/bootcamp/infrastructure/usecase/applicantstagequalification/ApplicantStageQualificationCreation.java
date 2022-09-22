package com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.applicantstagequalification;

import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.QualificationStatus;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield.QualificationFieldSchema;
import com.jalasoft.bootcamp.bootcamp.domain.stage.Stage;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApplicantStageQualificationCreation
{
    private final long applicantWorkflowId;
    private final Stage stage;
    private final String comment;
    private LocalDate appliedDate;
    private LocalDateTime submitDate;
    private final List<QualificationFieldSchema> qualificationFieldSchemas;
    private final QualificationStatus qualificationStatus;

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof ApplicantStageQualificationCreation))
        {
            return false;
        }
        ApplicantStageQualificationCreation that = (ApplicantStageQualificationCreation) o;
        return getApplicantWorkflowId() == that.getApplicantWorkflowId() && Objects
            .equals(getStage(), that.getStage()) && Objects
            .equals(getComment(), that.getComment()) && Objects
            .equals(getAppliedDate(), that.getAppliedDate()) && Objects
            .equals(getSubmitDate(), that.getSubmitDate()) && Objects
            .equals(getQualificationFieldSchemas(), that.getQualificationFieldSchemas())
            && getQualificationStatus() == that.getQualificationStatus();
    }

    @Override
    public int hashCode()
    {
        return Objects
            .hash(getApplicantWorkflowId(), getStage(), getComment(), getAppliedDate(),
                getSubmitDate(),
                getQualificationFieldSchemas(), getQualificationStatus());
    }
}
