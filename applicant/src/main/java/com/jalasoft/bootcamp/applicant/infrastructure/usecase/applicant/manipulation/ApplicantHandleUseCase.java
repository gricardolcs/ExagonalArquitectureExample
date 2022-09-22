package com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.manipulation;

import com.jalasoft.bootcamp.applicant.domain.applicant.Applicant;
import com.jalasoft.bootcamp.applicant.domain.applicant.ApplicantRepository;
import com.jalasoft.bootcamp.applicant.domain.applicant.CurriculumVitae;
import com.jalasoft.bootcamp.applicant.domain.applicant.Location;
import com.jalasoft.bootcamp.becommon.domain.ddd.IsUseCase;
import com.jalasoft.bootcamp.becommon.domain.exceptions.DuplicatedEntityException;
import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantDTO;
import com.jalasoft.bootcamp.becommon.infrastructure.factory.Factory;
import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.creation.ApplicantCreation;

import java.util.List;
import java.util.Set;

import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.Field;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@IsUseCase
@Service
public class ApplicantHandleUseCase
{
    private final Factory<Applicant, ApplicantCreation> applicantFactory;
    private final ApplicantRepository applicantRepository;

    @Autowired
    public ApplicantHandleUseCase(
        ApplicantRepository applicantRepository,
        Factory<Applicant, ApplicantCreation> applicantFactory)
    {
        this.applicantRepository = applicantRepository;
        this.applicantFactory = applicantFactory;
    }

    public Applicant create(final ApplicantDTO creationDTO)
    {
        String emailToCreate = creationDTO.getEmail();
        if (applicantRepository.existsByEmail(emailToCreate))
        {
            throw new DuplicatedEntityException(
                Field.EMAIL,
                ErrorsUtil.getDescription(
                    ErrorsUtil.ERROR_DESCRIPTION_APPLICANT_DUPLICATED,
                    Field.EMAIL,
                    String.valueOf(emailToCreate))
            );
        }
        Applicant applicant = applicantFactory.create(new ApplicantCreation(
            creationDTO.getFullName(),
            creationDTO.getBirthday(),
            creationDTO.getDegree(),
            creationDTO.getEmail(),
            creationDTO.getCountry(),
            creationDTO.getCity(),
            creationDTO.getPhoto(),
            creationDTO.getTelephone(),
            creationDTO.getCareer(),
            List.of(),
            Set.of(),
            List.of(),
            new CurriculumVitae(
                new byte[0],
                "",
                ""),
            List.of()));

        applicantRepository.save(applicant);
        return applicant;
    }

    public Applicant edit(long applicantId, final ApplicantDTO applicantDTO)
    {
        Applicant applicant = applicantRepository.findById(applicantId)
            .orElseThrow(() -> new EntityNotFoundException(
                Field.ID,
                ErrorsUtil.getDescription(
                    ErrorsUtil.ERROR_DESCRIPTION_APPLICANT_NOT_FOUND,
                    Field.ID,
                    String.valueOf(applicantId))
            ));
        applicant.setFullName(applicantDTO.getFullName());
        applicant.setBirthday(applicantDTO.getBirthday());
        applicant.setTelephone(applicantDTO.getTelephone());
        applicant.setLocation(new Location(
            applicantDTO.getCountry(),
            applicantDTO.getCity()));
        applicant.setCareer(applicantDTO.getCareer());
        applicant.setDegree(applicantDTO.getDegree());
        applicant.setPhoto(applicantDTO.getPhoto());
        return applicantRepository.save(applicant);
    }
}
