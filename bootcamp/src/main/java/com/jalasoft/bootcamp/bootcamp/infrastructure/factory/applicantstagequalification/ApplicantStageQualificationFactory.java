package com.jalasoft.bootcamp.bootcamp.infrastructure.factory.applicantstagequalification;

import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualification;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualificationAppliedDate;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualificationComment;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualificationSubmitDate;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield.QualificationField;
import com.jalasoft.bootcamp.becommon.domain.ddd.IsFactory;
import com.jalasoft.bootcamp.becommon.infrastructure.factory.Factory;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.applicantstagequalification.ApplicantStageQualificationCreation;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@IsFactory

@Service
@Qualifier("ApplicantStageQualificationFactory")
public class ApplicantStageQualificationFactory implements
    Factory<ApplicantStageQualification, ApplicantStageQualificationCreation>
{
    @Override
    public ApplicantStageQualification create(ApplicantStageQualificationCreation context)
    {
        Long applicantStageQualificationId = UUID.randomUUID().getLeastSignificantBits();
        List<QualificationField> qualificationFields = null;
        if (context.getQualificationFieldSchemas() != null)
        {
            qualificationFields = context.getQualificationFieldSchemas().stream()
                .map(qualificationFieldSchema ->
                    new QualificationField(UUID.randomUUID().getLeastSignificantBits(),
                        qualificationFieldSchema, applicantStageQualificationId))
                .collect(Collectors.toList());
        }

        return new ApplicantStageQualification(applicantStageQualificationId,
            new ApplicantStageQualificationComment(context.getComment()),
            new ApplicantStageQualificationAppliedDate(context.getAppliedDate()),
            new ApplicantStageQualificationSubmitDate(context.getSubmitDate()),
            context.getStage(),
            context.getApplicantWorkflowId(),
            qualificationFields,
            context.getQualificationStatus());
    }
}
