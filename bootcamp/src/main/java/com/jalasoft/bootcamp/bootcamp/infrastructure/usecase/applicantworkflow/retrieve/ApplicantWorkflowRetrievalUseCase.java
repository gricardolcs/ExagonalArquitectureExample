package com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.applicantworkflow.retrieve;

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

import java.util.Arrays;

@IsUseCase
@Service
public class ApplicantWorkflowRetrievalUseCase
{
    private final ApplicantWorkflowRepository applicantWorkflowRepository;

    private final BootcampRepository bootcampRepository;

    @Autowired
    public ApplicantWorkflowRetrievalUseCase(
        final ApplicantWorkflowRepository applicantWorkflowRepository,
        final BootcampRepository bootcampRepository)
    {
        this.applicantWorkflowRepository = applicantWorkflowRepository;
        this.bootcampRepository = bootcampRepository;
    }

    public ApplicantWorkflow retrieveApplicantWorkflowByBootcampIdAndApplicantId(
        final long bootcampId, final long applicantId)
    {
        Bootcamp bootcamp = bootcampRepository.findById(bootcampId)
            .orElseThrow(() -> new EntityNotFoundException(
                Field.ID,
                ErrorsUtil.getDescription(
                    ErrorsUtil.ERROR_DESCRIPTION_BOOTCAMP_NOT_FOUND,
                    Field.ID,
                    String.valueOf(bootcampId))
            ));

        return applicantWorkflowRepository.findByApplicantIdAndBootcampId(applicantId
            , bootcamp.getId()).orElseThrow(() -> new EntityNotFoundException(
            Field.WORKFLOW,
            ErrorsUtil.getDescription(
                ErrorsUtil.ERROR_DESCRIPTION_WORKFLOW_NOT_FOUND,
                Field.WORKFLOW,
                String.valueOf(bootcamp.getWorkflowId()))
        ));
    }
}
