package com.jalasoft.bootcamp.applicant.infrastructure.factory.applicantbootcamphistory;

import com.jalasoft.bootcamp.applicant.domain.applicantbootcamphistory.ApplicantBootcampHistory;
import com.jalasoft.bootcamp.becommon.domain.ddd.IsFactory;
import com.jalasoft.bootcamp.becommon.infrastructure.factory.Factory;
import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.upload.ApplicantBootcampHistoryCreation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@IsFactory
@Service
@Qualifier("ApplicantBootcampHistoryFactory")
public class ApplicantBootcampHistoryFactory
    implements Factory<ApplicantBootcampHistory, ApplicantBootcampHistoryCreation>
{
    @Override
    public ApplicantBootcampHistory create(ApplicantBootcampHistoryCreation context)
    {
        return new ApplicantBootcampHistory(UUID.randomUUID().getLeastSignificantBits(),
            context.getBootcampId(), context.getApplicantId(),
            context.getComplementaryAttributes());
    }
}
