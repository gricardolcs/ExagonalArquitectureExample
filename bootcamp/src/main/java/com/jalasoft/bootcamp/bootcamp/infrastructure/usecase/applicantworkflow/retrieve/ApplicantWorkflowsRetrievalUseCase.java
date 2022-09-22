package com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.applicantworkflow.retrieve;

import com.jalasoft.bootcamp.bootcamp.domain.applicantworkflow.ApplicantWorkflow;
import com.jalasoft.bootcamp.bootcamp.domain.applicantworkflow.ApplicantWorkflowRepository;
import com.jalasoft.bootcamp.becommon.domain.ddd.IsUseCase;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@IsUseCase
@Service
public class ApplicantWorkflowsRetrievalUseCase {

    private final ApplicantWorkflowRepository applicantWorkflowRepository;

    @Autowired
    public ApplicantWorkflowsRetrievalUseCase(
        ApplicantWorkflowRepository applicantWorkflowRepository) {
        this.applicantWorkflowRepository = applicantWorkflowRepository;
    }

    public List<ApplicantWorkflow> fetchAllApplicantWorkflowByApplicantId(long applicantId) {
        return applicantWorkflowRepository.findAllByApplicantId(applicantId);
    }
}
