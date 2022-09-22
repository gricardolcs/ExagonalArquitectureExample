package com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.applicantworkflow.retrieve;

import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualification;
import com.jalasoft.bootcamp.bootcamp.domain.applicantworkflow.ApplicantWorkflow;
import com.jalasoft.bootcamp.bootcamp.domain.applicantworkflow.ApplicantWorkflowRepository;
import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.Bootcamp;
import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.BootcampRepository;
import com.jalasoft.bootcamp.becommon.domain.ddd.IsUseCase;
import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.becommon.domain.exceptions.Errors;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.Field;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@IsUseCase
@Service
public class ApplicantStageQualificationRetrievalUseCase
{

    private final ApplicantWorkflowRepository applicantWorkflowRepository;

    private final BootcampRepository bootcampRepository;

    @Autowired
    public ApplicantStageQualificationRetrievalUseCase(
        final ApplicantWorkflowRepository applicantWorkflowRepository,
        final BootcampRepository bootcampRepository)
    {
        this.applicantWorkflowRepository = applicantWorkflowRepository;
        this.bootcampRepository = bootcampRepository;
    }

    public ApplicantStageQualification retrieveApplicantStageQualificationByApplicantIdAndStageId(
        final long bootcampId, final long applicantId, final long stageId)
    {

        List<Errors> errorsList = new ArrayList<>();

        try
        {
            Optional<Bootcamp> bootcamp = bootcampRepository.findById(bootcampId);
            if (!bootcamp.isPresent())
            {
                errorsList.add(new Errors(
                    Field.ID,
                    ErrorsUtil.getDescription(
                        ErrorsUtil.ERROR_DESCRIPTION_BOOTCAMP_NOT_FOUND,
                        Field.ID,
                        String.valueOf(bootcampId))));
            }

            Optional<ApplicantWorkflow> applicantWorkflow = applicantWorkflowRepository
                .findByApplicantIdAndBootcampId(applicantId, bootcamp.get().getId());

            if (!applicantWorkflow.isPresent())
            {
                errorsList.add(new Errors(
                    Field.WORKFLOW,
                    ErrorsUtil.getDescription(
                        ErrorsUtil.ERROR_DESCRIPTION_WORKFLOW_NOT_FOUND,
                        Field.WORKFLOW,
                        String.valueOf(applicantWorkflow.get().getId()))));
            }

            Optional<ApplicantStageQualification> applicantStageQualification =
                applicantWorkflow.get().getApplicantStageQualificationList().stream()
                    .filter(stageQualification ->
                        stageQualification.getStage().getId() == stageId)
                    .findFirst();

            if (!applicantStageQualification.isPresent())
            {
                errorsList.add(
                    new Errors(
                        Field.STAGE_QUALIFICATION,
                        ErrorsUtil.getDescription(
                            ErrorsUtil.ERROR_DESCRIPTION_STAGE_QUALIFICATION_NOT_FOUND,
                            Field.STAGE_QUALIFICATION,
                            String.valueOf(applicantStageQualification.get().getId()))));
            }
            return applicantStageQualification.get();
        }
        catch (NoSuchElementException exception)
        {
            throw new EntityNotFoundException(errorsList);
        }
    }
}
