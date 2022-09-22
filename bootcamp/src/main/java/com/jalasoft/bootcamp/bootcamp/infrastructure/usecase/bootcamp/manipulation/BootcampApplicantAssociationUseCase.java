package com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.bootcamp.manipulation;

import static com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.QualificationStatus.IN_PROGRESS;

import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualification;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.QualificationStatus;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield.QualificationFieldSchema;
import com.jalasoft.bootcamp.bootcamp.domain.applicantworkflow.ApplicantWorkflow;
import com.jalasoft.bootcamp.bootcamp.domain.applicantworkflow.ApplicantWorkflowRepository;
import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.Bootcamp;
import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.BootcampRepository;
import com.jalasoft.bootcamp.becommon.domain.ddd.IsUseCase;
import com.jalasoft.bootcamp.bootcamp.domain.department.Department;
import com.jalasoft.bootcamp.bootcamp.domain.department.DepartmentRepository;
import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.bootcamp.domain.projecttype.ProjectType;
import com.jalasoft.bootcamp.bootcamp.domain.projecttype.ProjectTypeRepository;
import com.jalasoft.bootcamp.bootcamp.domain.stage.Stage;
import com.jalasoft.bootcamp.bootcamp.domain.workflow.Workflow;
import com.jalasoft.bootcamp.bootcamp.domain.workflow.WorkflowRepository;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.bootcamp.BootcampApplicantAssociationDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.eventpublisher.EventPublisher;
import com.jalasoft.bootcamp.bootcamp.infrastructure.events.ApplicantStageQualifiedEventList;
import com.jalasoft.bootcamp.bootcamp.infrastructure.events.ApplicantStageQualifiedEvent;
import com.jalasoft.bootcamp.becommon.infrastructure.factory.Factory;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.applicantstagequalification.ApplicantStageQualificationCreation;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.applicantworkflow.grading.ApplicantWorkflowCreation;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.Field;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@IsUseCase
@Service
public class BootcampApplicantAssociationUseCase
{
    public static final int FIRST = 1;
    private final BootcampRepository bootcampRepository;
    private final WorkflowRepository workflowRepository;
    private final DepartmentRepository departmentRepository;
    private final ProjectTypeRepository projectTypeRepository;
    private final EventPublisher eventPublisher;
    private final Factory<ApplicantWorkflow, ApplicantWorkflowCreation> applicantWorkflowFactory;
    private final Factory<ApplicantStageQualification, ApplicantStageQualificationCreation>
        applicantStageQualificationFactory;
    private final ApplicantWorkflowRepository applicantWorkflowRepository;

    @Autowired
    public BootcampApplicantAssociationUseCase(
        final BootcampRepository bootcampRepository,
        final WorkflowRepository workflowRepository,
        final DepartmentRepository departmentRepository,
        final ProjectTypeRepository projectTypeRepository,
        final ApplicantWorkflowRepository applicantWorkflowRepository,
        final Factory<ApplicantWorkflow, ApplicantWorkflowCreation> applicantWorkflowFactory,
        final Factory<ApplicantStageQualification, ApplicantStageQualificationCreation>
            applicantStageQualificationFactory,
        final EventPublisher eventPublisher)
    {
        this.bootcampRepository = bootcampRepository;
        this.workflowRepository = workflowRepository;
        this.departmentRepository = departmentRepository;
        this.projectTypeRepository = projectTypeRepository;
        this.applicantWorkflowRepository = applicantWorkflowRepository;
        this.applicantWorkflowFactory = applicantWorkflowFactory;
        this.applicantStageQualificationFactory = applicantStageQualificationFactory;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public Bootcamp associate(final BootcampApplicantAssociationDTO associationDTO)
    {
        Bootcamp bootcamp = bootcampRepository
            .findById(associationDTO.getBootcampId())
            .orElseThrow(() -> new EntityNotFoundException(
                Field.ID,
                ErrorsUtil.getDescription(
                    ErrorsUtil.ERROR_DESCRIPTION_BOOTCAMP_NOT_FOUND,
                    Field.ID,
                    String.valueOf(associationDTO.getBootcampId()))
            ));
        Workflow workflow = workflowRepository
            .findById(bootcamp.getWorkflowId())
            .orElseThrow(() -> new EntityNotFoundException(
                Field.WORKFLOW,
                ErrorsUtil.getDescription(
                    ErrorsUtil.ERROR_DESCRIPTION_BOOTCAMP_NOT_FOUND,
                    Field.WORKFLOW,
                    String.valueOf(bootcamp.getWorkflowId()))
            ));
        Department department = departmentRepository
            .findById(bootcamp.getDepartmentId())
            .orElseThrow(() -> new EntityNotFoundException(
                Field.DEPARTMENT_ID,
                ErrorsUtil.getDescription(
                    ErrorsUtil.ERROR_DESCRIPTION_DEPARTMENT_NOT_FOUND,
                    Field.DEPARTMENT_ID,
                    String.valueOf(bootcamp.getDepartmentId()))
            ));
        ProjectType projectType = projectTypeRepository
            .findById(bootcamp.getProjectTypeId())
            .orElseThrow(() -> new EntityNotFoundException(
                Field.PROJECT_TYPE_ID,
                ErrorsUtil.getDescription(
                    ErrorsUtil.ERROR_DESCRIPTION_PROJECT_TYPE_NOT_FOUND,
                    Field.PROJECT_TYPE_ID,
                    String.valueOf(bootcamp.getDepartmentId()))
            ));
        Stage firstStage = workflow.getStages().stream()
            .filter(stage -> stage.getStageOrder().getOrder() == FIRST).findFirst()
            .orElseThrow(() -> new EntityNotFoundException(
                Field.STAGE,
                ErrorsUtil.ERROR_DESCRIPTION_STAGE_NOT_FOUND));

        List<ApplicantStageQualifiedEvent> applicantStageQualifiedEvents = new ArrayList<>();
        List<QualificationFieldSchema> qualificationFieldSchemas = firstStage.getSchemas().stream()
            .map((inputField -> inputField.getInputFieldSchema().createEmptyQualificationFieldSchema()))
            .collect(Collectors.toList());
        Set<Long> applicantIds = associationDTO.getApplicantIds()
            .stream().map(applicantId -> {
                if (!bootcamp.getBootcampMembers().contains(applicantId))
                {
                    ApplicantWorkflow applicantWorkflow = createApplicantWorkflow(
                        applicantId,
                        bootcamp);
                    saveApplicantStageQualification(applicantWorkflow, firstStage, IN_PROGRESS,
                        qualificationFieldSchemas, "", LocalDate.now(), null);
                    applicantStageQualifiedEvents
                        .add(new ApplicantStageQualifiedEvent(bootcamp.getId(), firstStage.getId(),
                            IN_PROGRESS, applicantId, firstStage.getStageName().getName()));
                }
                return applicantId;
            }).collect(Collectors.toSet());
        eventPublisher.publishEvent(new ApplicantStageQualifiedEventList(
            applicantStageQualifiedEvents));
        bootcamp.addMembers(applicantIds);
        bootcampRepository.save(bootcamp);
        return bootcamp;
    }

    private ApplicantWorkflow createApplicantWorkflow(
        final long applicantId,
        final Bootcamp bootcamp)
    {
        ApplicantWorkflow applicantWorkflow = applicantWorkflowFactory
            .create(new ApplicantWorkflowCreation(applicantId, bootcamp));

        return applicantWorkflowRepository.save(applicantWorkflow);
    }

    private void saveApplicantStageQualification(
        final ApplicantWorkflow applicantWorkflow, final Stage stage,
        QualificationStatus qualificationStatus,
        final List<QualificationFieldSchema> qualificationFieldSchemas,
        final String qualificationComment, final LocalDate appliedDate,
        final LocalDateTime submitDate)
    {

        ApplicantStageQualification newQualification =
            createQualification(applicantWorkflow.getId(), stage, qualificationComment, appliedDate,
                submitDate, qualificationFieldSchemas, qualificationStatus);
        applicantWorkflow.addApplicantStageQualification(newQualification);
        applicantWorkflowRepository.save(applicantWorkflow);
    }

    private ApplicantStageQualification createQualification(
        final long applicantWorkflowId, final Stage stage, final String qualificationComment,
        final LocalDate appliedDate, final LocalDateTime submitDate,
        final List<QualificationFieldSchema> qualificationFieldSchemas,
        final QualificationStatus qualificationStatus
    )
    {
        return applicantStageQualificationFactory
            .create(new ApplicantStageQualificationCreation(
                applicantWorkflowId, stage, qualificationComment, appliedDate, submitDate,
                qualificationFieldSchemas, qualificationStatus));
    }
}
