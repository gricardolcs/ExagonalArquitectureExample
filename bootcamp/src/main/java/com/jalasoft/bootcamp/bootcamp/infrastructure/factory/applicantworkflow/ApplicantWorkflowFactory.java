package com.jalasoft.bootcamp.bootcamp.infrastructure.factory.applicantworkflow;

import com.jalasoft.bootcamp.bootcamp.domain.applicantworkflow.ApplicantWorkflow;
import com.jalasoft.bootcamp.becommon.domain.ddd.IsFactory;
import com.jalasoft.bootcamp.becommon.infrastructure.factory.Factory;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.applicantworkflow.grading.ApplicantWorkflowCreation;
import java.util.Collections;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@IsFactory

@Service
@Qualifier("ApplicantWorkflowFactory")
public class ApplicantWorkflowFactory implements
    Factory<ApplicantWorkflow, ApplicantWorkflowCreation>
{
    @Override
    public ApplicantWorkflow create(ApplicantWorkflowCreation context)
    {
        return new ApplicantWorkflow(UUID.randomUUID().getLeastSignificantBits(),
            context.getBootcamp().getId(), context.getApplicantId(), Collections.emptySet());
    }
}
