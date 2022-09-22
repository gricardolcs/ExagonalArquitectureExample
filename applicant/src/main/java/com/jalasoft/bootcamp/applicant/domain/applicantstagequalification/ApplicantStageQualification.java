package com.jalasoft.bootcamp.applicant.domain.applicantstagequalification;

import com.jalasoft.bootcamp.becommon.domain.ddd.IsAggregateRoot;
import com.jalasoft.bootcamp.becommon.domain.ddd.IsValueObject;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import lombok.Getter;
import org.springframework.data.annotation.PersistenceConstructor;

@Getter
@IsAggregateRoot
@IsValueObject
public class ApplicantStageQualification
{
    private final Long stageId;
    private final StageName stageName;
    private final Long applicantId;
    private final Long bootcampId;
    @ApiModelProperty(
        allowableValues = "IN_PROGRESS, QUALIFIED_AND_READONLY, PASSED, FAILED, WITHDRAW",
        example = "IN_PROGRESS"
    )
    private QualificationStatus qualificationStatus;

    @PersistenceConstructor
    public ApplicantStageQualification(
        Long stageId, StageName stageName,
        QualificationStatus qualificationStatus, Long applicantId, Long bootcampId)
    {
        this.stageId = stageId;
        this.stageName = stageName;
        this.qualificationStatus = qualificationStatus;
        this.applicantId = applicantId;
        this.bootcampId = bootcampId;
    }

    public void changeQualificationStatus(QualificationStatus qualificationStatus)
    {
        this.qualificationStatus = qualificationStatus;
    }

    /*
     * The override is necessary for unit tests performed with the Mockito tool, this allows
     * to compare objects through the tests
     */
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
        ApplicantStageQualification that = (ApplicantStageQualification) o;
        return Objects.equals(stageId, that.stageId) && Objects
            .equals(stageName, that.stageName) && Objects
            .equals(applicantId, that.applicantId) && Objects
            .equals(bootcampId, that.bootcampId) && qualificationStatus == that.qualificationStatus;
    }

    /*
     * The override is necessary for unit tests performed with the Mockito tool, this allows
     * to compare objects through the tests
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(stageId, stageName, applicantId, bootcampId, qualificationStatus);
    }
}