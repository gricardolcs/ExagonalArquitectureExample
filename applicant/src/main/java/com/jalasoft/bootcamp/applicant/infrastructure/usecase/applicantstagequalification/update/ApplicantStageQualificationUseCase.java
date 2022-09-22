package com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicantstagequalification.update;

import static com.jalasoft.bootcamp.applicant.domain.applicantstagequalification.QualificationStatus.DELETED;

import com.jalasoft.bootcamp.applicant.domain.applicant.Applicant;
import com.jalasoft.bootcamp.applicant.domain.applicant.ApplicantRepository;
import com.jalasoft.bootcamp.applicant.domain.applicantstagequalification.ApplicantStageQualification;
import com.jalasoft.bootcamp.applicant.domain.applicantstagequalification.StageName;
import com.jalasoft.bootcamp.becommon.domain.ddd.IsUseCase;
import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicantstagequalification.ApplicantStageQualificationDTO;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.Field;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@IsUseCase
@Service
public class ApplicantStageQualificationUseCase
{
    private final ApplicantRepository applicantRepository;

    @Autowired
    public ApplicantStageQualificationUseCase(final ApplicantRepository applicantRepository)
    {
        this.applicantRepository = applicantRepository;
    }

    @Transactional
    public void addOrUpdateApplicantStageQualified(
        ApplicantStageQualificationDTO applicantStageQualificationDTO)
    {
        Long applicantId = applicantStageQualificationDTO.getApplicantId();
        Applicant applicant = applicantRepository.findById(applicantId)
            .orElseThrow(() -> new EntityNotFoundException(
                    Field.ID,
                    ErrorsUtil.getDescription(
                        ErrorsUtil.ERROR_DESCRIPTION_APPLICANT_NOT_FOUND,
                        Field.ID, String.valueOf(applicantId))
            ));

        Long bootcampId = applicantStageQualificationDTO.getBootcampId();
        if (!applicant.getIncludedInBootcampIds().contains(bootcampId))
        {
            throw new EntityNotFoundException(
                    Field.ID,
                    ErrorsUtil.getDescription(
                            ErrorsUtil.ERROR_DESCRIPTION_APPLICANT_NOT_FOUND,
                            Field.ID, String.valueOf(applicantId))
                        .concat(ErrorsUtil.getDescription(
                            ErrorsUtil.ERROR_DESCRIPTION_APPLICANT_NOT_FOUND_IN_BOOTCAMP,
                            Field.ID, String.valueOf(bootcampId))
                        ));
        }

        Long stageId = applicantStageQualificationDTO.getStageId();
        Optional<ApplicantStageQualification> qualificationOptional = applicant
            .getApplicantStageQualifications().stream()
            .filter((qualification) -> qualification.getStageId().equals(stageId)
                && qualification.getBootcampId().equals(bootcampId))
            .findFirst();

        if (qualificationOptional.isPresent())
        {
            ApplicantStageQualification qualification = qualificationOptional.get();
            if (applicantStageQualificationDTO.getQualificationStatus()
                .equals(DELETED))
            {
                applicant.getApplicantStageQualifications().remove(qualification);
            }
            else
            {
                qualification.changeQualificationStatus(
                    applicantStageQualificationDTO.getQualificationStatus());
            }
        }
        else
        {
            applicant.addApplicantStageQualifiedId(new ApplicantStageQualification(stageId
                , new StageName(applicantStageQualificationDTO.getStageName())
                , applicantStageQualificationDTO.getQualificationStatus(), applicantId
                , bootcampId));
        }
        applicantRepository.save(applicant);
    }
}
