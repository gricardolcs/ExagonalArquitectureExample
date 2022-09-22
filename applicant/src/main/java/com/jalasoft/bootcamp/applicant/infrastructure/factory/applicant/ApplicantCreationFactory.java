package com.jalasoft.bootcamp.applicant.infrastructure.factory.applicant;

import com.jalasoft.bootcamp.applicant.domain.applicant.Applicant;
import com.jalasoft.bootcamp.applicant.domain.applicant.Location;
import com.jalasoft.bootcamp.becommon.domain.ddd.IsFactory;
import com.jalasoft.bootcamp.becommon.infrastructure.factory.Factory;
import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.creation.ApplicantCreation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.UUID;

@IsFactory
@Service
@Qualifier("ApplicantCreationFactory")
public class ApplicantCreationFactory implements Factory<Applicant, ApplicantCreation>
{
    @Override
    @Transactional
    public Applicant create(ApplicantCreation context)
    {
        final Long applicantId = UUID.randomUUID().getLeastSignificantBits();

        return new Applicant(applicantId, context.getFullName(),
            context.getBirthday(), context.getDegree(),
            context.getEmail(), new Location(context.getCountry(), context.getCity()),
            context.getPhoto(), context.getTelephone(),
            context.getCareer(), Collections.emptyList(),
            context.getIncludedInBootcampIds(), Collections.emptySet(), Collections.emptyList(),
            context.getCurriculumVitae(), null);
    }
}
