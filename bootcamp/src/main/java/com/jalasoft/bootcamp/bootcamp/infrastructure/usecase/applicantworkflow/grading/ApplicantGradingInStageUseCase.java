package com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.applicantworkflow.grading;

import static com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.QualificationStatus.DELETED;
import static com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.QualificationStatus.FAILED;
import static com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.QualificationStatus.IN_PROGRESS;
import static com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.QualificationStatus.ON_HOLD;
import static com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.QualificationStatus.PASSED;
import static com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.QualificationStatus.QUALIFIED_AND_READONLY;
import static com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.QualificationStatus.WITHDRAW;
import static com.jalasoft.bootcamp.bootcamp.domain.stage.schema.SchemaFieldType.NUMERIC_INPUT;
import static com.jalasoft.bootcamp.bootcamp.domain.stage.schema.SchemaFieldType.DROPDOWN_INPUT;

import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualification;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualificationAppliedDate;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualificationComment;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualificationSubmitDate;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.QualificationStatus;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield.QualificationFieldSchema;
import com.jalasoft.bootcamp.bootcamp.domain.applicantworkflow.ApplicantWorkflow;
import com.jalasoft.bootcamp.bootcamp.domain.applicantworkflow.ApplicantWorkflowRepository;
import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.Bootcamp;
import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.BootcampRepository;
import com.jalasoft.bootcamp.becommon.domain.ddd.IsUseCase;
import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.becommon.domain.exceptions.Errors;
import com.jalasoft.bootcamp.becommon.domain.exceptions.InvalidArgumentsEntityException;
import com.jalasoft.bootcamp.bootcamp.domain.stage.Stage;
import com.jalasoft.bootcamp.bootcamp.domain.workflow.Workflow;
import com.jalasoft.bootcamp.bootcamp.domain.workflow.WorkflowRepository;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.applicantstagequalification.ApplicantStageQualificationCreationDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.eventpublisher.EventPublisher;
import com.jalasoft.bootcamp.bootcamp.infrastructure.events.ApplicantStageQualifiedEvent;
import com.jalasoft.bootcamp.bootcamp.infrastructure.events.ApplicantStageQualifiedEventList;
import com.jalasoft.bootcamp.becommon.infrastructure.factory.Factory;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.applicantstagequalification.ApplicantStageQualificationCreation;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@IsUseCase
@Service
public class ApplicantGradingInStageUseCase
{
    private final static int NON_EXISTING_STAGE_ID = 0;

    private final static int MIN_SCORE_QUALIFICATION = 0;

    private final ApplicantWorkflowRepository applicantWorkflowRepository;

    private final WorkflowRepository workflowRepository;

    private final BootcampRepository bootcampRepository;

    private final Factory<ApplicantWorkflow, ApplicantWorkflowCreation> factory;

    private final Factory<ApplicantStageQualification, ApplicantStageQualificationCreation>
        applicantStageQualificationFactory;

    private final EventPublisher eventPublisher;

    @Autowired
    public ApplicantGradingInStageUseCase(
        final ApplicantWorkflowRepository applicantWorkflowRepository,
        final BootcampRepository bootcampRepository, final WorkflowRepository workflowRepository,
        final Factory<ApplicantWorkflow, ApplicantWorkflowCreation> factory,
        final Factory<ApplicantStageQualification, ApplicantStageQualificationCreation>
            applicantStageQualificationFactory, EventPublisher eventPublisher)
    {
        this.applicantWorkflowRepository = applicantWorkflowRepository;
        this.bootcampRepository = bootcampRepository;
        this.workflowRepository = workflowRepository;
        this.factory = factory;
        this.applicantStageQualificationFactory = applicantStageQualificationFactory;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public ApplicantStageQualification gradeApplicantInStage(
        final long bootcampId, final long stageId, final long applicantId,
        final ApplicantStageQualificationCreationDTO applicantStageQualificationCreationDTO)
    {

        if (!isValidScoreValues(applicantStageQualificationCreationDTO.getSchema()))
        {
            throw new InvalidArgumentsEntityException(
                Field.STAGE_QUALIFICATION,
                ErrorsUtil.ERROR_DESCRIPTION_INVALID_SCORE);
        }

        Bootcamp bootcamp = bootcampRepository.findById(bootcampId)
            .orElseThrow(() -> new EntityNotFoundException(
                Field.ID,
                ErrorsUtil.getDescription(
                    ErrorsUtil.ERROR_DESCRIPTION_BOOTCAMP_NOT_FOUND,
                    Field.ID,
                    String.valueOf(bootcampId))
            ));

        Workflow workflow = workflowRepository.findById(bootcamp.getWorkflowId())
            .orElseThrow(() -> new EntityNotFoundException(
                Field.WORKFLOW,
                ErrorsUtil.getDescription(
                    ErrorsUtil.ERROR_DESCRIPTION_WORKFLOW_NOT_FOUND,
                    Field.WORKFLOW,
                    String.valueOf(bootcamp.getWorkflowId()))
            ));

        Stage stage = workflow.getStageOf(stageId)
            .orElseThrow(() -> new EntityNotFoundException(
                Field.STAGE,
                ErrorsUtil.ERROR_DESCRIPTION_STAGE_NOT_FOUND));

        ApplicantWorkflow applicantWorkflow = createOrGetApplicantWorkflow(applicantId, bootcamp);

        QualificationStatus qualificationStatus = applicantStageQualificationCreationDTO
            .getQualificationStatus();

        String qualificationComment = applicantStageQualificationCreationDTO.getComment();
        Optional<QualificationFieldSchema> fieldSchemaDTOOptional =
            applicantStageQualificationCreationDTO
                .getSchema().stream().filter(qualificationFieldSchema ->
                    qualificationFieldSchema.getType().equals(DROPDOWN_INPUT)
                        && qualificationFieldSchema.getLabel().contains("Status:")).findFirst();
        if (fieldSchemaDTOOptional.isPresent())
        {
            QualificationFieldSchema qualificationFieldSchemaDTO = fieldSchemaDTOOptional.get();
            if (qualificationFieldSchemaDTO.obtainValue().equals("Signed"))
            {
                bootcamp.addAcceptedParticipant(applicantId);
            }
            else if (qualificationFieldSchemaDTO.obtainValue().equals("Declined")
                || qualificationFieldSchemaDTO.obtainValue().equals("On Hold"))
            {
                bootcamp.removeAcceptedParticipants(applicantId);
            }
            bootcampRepository.save(bootcamp);
        }

        if (applicantStageQualificationCreationDTO.getQualificationStatus().equals(WITHDRAW))
        {
            bootcamp.removeAcceptedParticipants(applicantId);
        }

        ApplicantStageQualification qualification =
            saveOrUpdateQualification(applicantWorkflow, stage, qualificationStatus,
                applicantStageQualificationCreationDTO.getSchema(), qualificationComment,
                applicantStageQualificationCreationDTO.getAppliedDate(),
                applicantStageQualificationCreationDTO.getSubmitDate());

        List<ApplicantStageQualifiedEvent> applicantStageQualifiedEvents = new ArrayList<>();
        applicantStageQualifiedEvents.add(new ApplicantStageQualifiedEvent(bootcampId, stageId,
            qualification.getQualificationStatus(), applicantId, stage.getStageName().getName()));

        long nextStageId = applicantStageQualificationCreationDTO.getNextStageId();
        configureNextQualification(workflow, nextStageId, qualification.getQualificationStatus(),
            applicantWorkflow, bootcampId, applicantId,
            stage.getStageOrder().getOrder() + 1, applicantStageQualifiedEvents,
            applicantStageQualificationCreationDTO.getSubmitDate().toLocalDate());

        updatePreviousQualificationsStatus(applicantWorkflow, bootcampId, applicantId,
            applicantStageQualifiedEvents);

        eventPublisher
            .publishEvent(new ApplicantStageQualifiedEventList(applicantStageQualifiedEvents));
        return qualification;
    }

    private boolean isValidScoreValues(List<QualificationFieldSchema> qualificationFieldSchemas)
    {
        return qualificationFieldSchemas.stream().noneMatch(qualificationFieldSchema ->
            NUMERIC_INPUT.equals(qualificationFieldSchema.getType())
                && ((Integer) qualificationFieldSchema.obtainValue()) < MIN_SCORE_QUALIFICATION);
    }

    private ApplicantWorkflow createOrGetApplicantWorkflow(
        final long applicantId, final Bootcamp bootcamp)
    {
        if (!applicantWorkflowRepository.existsByApplicantIdAndBootcampId(
            applicantId,
            bootcamp.getId()))
        {
            return createApplicantWorkflow(applicantId, bootcamp);
        }

        return applicantWorkflowRepository.findByApplicantIdAndBootcampId(
            applicantId,
            bootcamp.getId()).get();
    }

    private ApplicantWorkflow createApplicantWorkflow(
        final long applicantId, final Bootcamp bootcamp)
    {
        ApplicantWorkflow applicantWorkflow = factory
            .create(new ApplicantWorkflowCreation(applicantId, bootcamp));

        return applicantWorkflowRepository.save(applicantWorkflow);
    }

    private ApplicantStageQualification saveOrUpdateQualification(
        final ApplicantWorkflow applicantWorkflow
        , final Stage stage, QualificationStatus qualificationStatus
        , final List<QualificationFieldSchema> qualificationFieldSchemas
        , final String qualificationComment, final LocalDate appliedDate
        , final LocalDateTime submitDate) {
        ApplicantStageQualification qualification = findQualification(applicantWorkflow, stage)
            .get();
        if (isQualificationEditable(qualification)) {
            qualification
                .changeQualificationFieldSchemas(qualificationFieldSchemas)
                .changeQualificationStatus(qualificationStatus)
                .changeAppliedDate(new ApplicantStageQualificationAppliedDate(appliedDate))
                .changeSubmitDate(new ApplicantStageQualificationSubmitDate(submitDate))
                .changeComment(new ApplicantStageQualificationComment(qualificationComment));

            applicantWorkflow.addApplicantStageQualification(qualification);
            applicantWorkflowRepository.save(applicantWorkflow);
        }
        return qualification;
    }

    private Optional<ApplicantStageQualification> findQualification(
        final ApplicantWorkflow applicantWorkflow, final Stage stage) {
        return applicantWorkflow.getApplicantStageQualificationList().stream()
            .filter(qualification ->
                qualification.getApplicantWorkflowId().equals(applicantWorkflow.getId())
                    && qualification.getStage().equals(stage))
            .findFirst();
    }

    private boolean isQualificationEditable(ApplicantStageQualification qualification)
    {
        return !qualification.getQualificationStatus()
            .equals(QUALIFIED_AND_READONLY);
    }

    private ApplicantStageQualification createQualification(
        final long applicantWorkflowId, final Stage stage, final String qualificationComment,
        final LocalDate appliedDate, final LocalDateTime submitDate,
        final List<QualificationFieldSchema> qualificationFieldSchemas,
        final QualificationStatus qualificationStatus
    )
    {
        return applicantStageQualificationFactory
            .create(new ApplicantStageQualificationCreation(applicantWorkflowId, stage,
                qualificationComment, appliedDate, submitDate, qualificationFieldSchemas,
                qualificationStatus));
    }

    /**
     * Configure the next {@link ApplicantStageQualification} to the current, first saves the next
     * qualification if the current qualification has an status as PASSED, and second if the
     * current qualification has a status as FAILED then the next qualification is removed
     * from the list if this exists
     */
    private void configureNextQualification(
        final Workflow workflow, final long nextStageId,
        final QualificationStatus currentQualificationStatus,
        final ApplicantWorkflow applicantWorkflow, final long bootcampId,
        final long applicantId, final int nextStageOrder,
        List<ApplicantStageQualifiedEvent> applicantStageQualifiedEvents,
        final LocalDate appliedDate)
    {
        if (currentQualificationStatus.equals(PASSED) &&
            nextStageId != NON_EXISTING_STAGE_ID)
        {
            Stage nextStage = workflow.getStageOf(nextStageId)
                .orElseThrow(() -> new EntityNotFoundException(
                    Field.STAGE_QUALIFICATION,
                    ErrorsUtil.getDescription(
                        ErrorsUtil.ERROR_DESCRIPTION_STAGE_QUALIFICATION_NOT_FOUND,
                        Field.STAGE_QUALIFICATION,
                        String.valueOf(nextStageId))

                ));
            ApplicantStageQualification nextQualification =
                saveNextQualification(applicantWorkflow, nextStage, appliedDate, nextStageOrder);

            if (nextQualification != null)
            {
                applicantStageQualifiedEvents.add(new ApplicantStageQualifiedEvent(bootcampId,
                    nextStageId, nextQualification.getQualificationStatus(), applicantId,
                    nextQualification.getStage().getStageName().getName()));
            }
        }

        if (currentQualificationStatus.equals(FAILED) &&
            nextStageId != NON_EXISTING_STAGE_ID || currentQualificationStatus.equals(ON_HOLD))
        {
            Stage nextStage = workflow.getStageOf(nextStageId)
                .orElseThrow(() -> new EntityNotFoundException(
                    Field.STAGE_QUALIFICATION,
                    ErrorsUtil.getDescription(
                        ErrorsUtil.ERROR_DESCRIPTION_STAGE_QUALIFICATION_NOT_FOUND,
                        Field.STAGE_QUALIFICATION,
                        String.valueOf(nextStageId))

                ));
            removeNextQualification(applicantWorkflow, nextStage
                , bootcampId, applicantId, applicantStageQualifiedEvents);
        }
    }

    /**
     * Saves the next {@link ApplicantStageQualification} when the previous stage for the applicant
     * was qualified with a {@link QualificationStatus} as PASSED in order to continue the {@link
     * ApplicantWorkflow}
     */
    private ApplicantStageQualification saveNextQualification(
        final ApplicantWorkflow applicantWorkflow, final Stage nextStage,
        final LocalDate appliedDate, final int nextStageOrder)
    {
        if (nextStageOrder != nextStage.getStageOrder().getOrder())
        {
            throw new InvalidArgumentsEntityException(
                Field.STAGE_QUALIFICATION,
                ErrorsUtil.ERROR_DESCRIPTION_INVALID_NEXT_QUALIFICATION);
        }

        Optional<ApplicantStageQualification> qualification =
            findQualification(applicantWorkflow, nextStage);

        if (qualification.isEmpty())
        {
            List<QualificationFieldSchema> qualificationFieldSchemas = nextStage.getSchemas()
                .stream().map(
                    inputField -> inputField.getInputFieldSchema().createEmptyQualificationFieldSchema())
                .collect(Collectors.toList());
            ApplicantStageQualification newApplicantStageQualification =
                createQualification(applicantWorkflow.getId(), nextStage, "",
                    appliedDate, null, qualificationFieldSchemas, IN_PROGRESS);

            applicantWorkflow.addApplicantStageQualification(newApplicantStageQualification);
            applicantWorkflowRepository.save(applicantWorkflow);
            return newApplicantStageQualification;
        }

        return qualification.get();
    }

    /**
     * Remove the next {@link ApplicantStageQualification} to the current and an event is added to
     * the event list to notify to applicant service that the next qualification was removed
     */
    private void removeNextQualification(
        final ApplicantWorkflow applicantWorkflow,
        final Stage nextStage, final long bootcampId, final long applicantId,
        final List<ApplicantStageQualifiedEvent> applicantStageQualifiedEvents)
    {
        Optional<ApplicantStageQualification> nextQualificationOptional =
            findQualification(applicantWorkflow, nextStage);

        if (nextQualificationOptional.isPresent())
        {
            ApplicantStageQualification nextQualification = nextQualificationOptional.get();
            long applicantStageQualificationId = nextQualification.getId();
            applicantWorkflowRepository.deleteQualificationFieldsByApplicantStageQualificationId(
                applicantStageQualificationId);
            applicantWorkflowRepository.deleteApplicantStageQualificationById(
                applicantStageQualificationId);

            applicantStageQualifiedEvents.add(new ApplicantStageQualifiedEvent(bootcampId,
                nextStage.getId(), DELETED, applicantId, nextStage.getStageName().getName()));
        }
    }

    /**
     * Update all the previous {@link ApplicantStageQualification} to the current, sets the status
     * of all these qualifications as QUALIFIED_AND_READONLY they are not editable any more. To set
     * a qualification as QUALIFIED_AND_READONLY status it must meet two criteria. First is when the
     * workflow has only two qualifications and the last one has a FAILED status. The second
     * one is when the workflow has more that two qualifications and the last one has a IN_PROGRESS
     * status or a FAILED status. After that calculation the status of each qualification in
     * the list of previous qualifications are updated and are added to the event list to notify the
     * changes to applicant service
     */
    private void updatePreviousQualificationsStatus(
        final ApplicantWorkflow applicantWorkflow, final long bootcampId, final long applicantId,
        final List<ApplicantStageQualifiedEvent> applicantStageQualifiedEvents)
    {
        List<ApplicantStageQualification> previousQualifications = new ArrayList<>();
        ApplicantStageQualification lastQualificationCreated =
            getLastQualificationCreated(applicantWorkflow);

        if (lastQualificationCreated != null)
        {
            int lastQualificationOrder = lastQualificationCreated.getStage().getStageOrder()
                .getOrder();
            QualificationStatus lastQualificationStatus = lastQualificationCreated
                .getQualificationStatus();
            int maxLimitOrder = 0;
            int totalQualifications = applicantWorkflow.getApplicantStageQualificationList().size();
          /* Case 1: When workflow has 2 qualifications eg. X and Y, if Y has a FAILED
          status then X qualification must updated to READONLY, the maxLimitOrder variable
          will help to delimit what previous qualification has to be updated,
          in this particular case the limit is 1 */
            if (totalQualifications == 2
                && lastQualificationStatus.equals(FAILED))
            {
                maxLimitOrder = lastQualificationOrder - 1;
            }
          /* Case 2: When workflow has more than 2 qualifications eg. X, Y and Z,
          (being Z as last qualification) X and Y has a PASSED status and Z is IN_PROGRESS.
          CASE 2.1: When Z is still IN_PROGRESS then only X qualification must
          be updated to READONLY, and Y is still editable.
          CASE 2.2: When Z is FAILED then X and Y must be updated as READONLY */
            if (totalQualifications > 2)
            {
                if (lastQualificationStatus.equals(IN_PROGRESS))
                {
                    maxLimitOrder = lastQualificationOrder - 2;
                }
                else if (lastQualificationStatus.equals(FAILED))
                {
                    maxLimitOrder = lastQualificationOrder - 1;
                }
            }
            previousQualifications = getPreviousQualifications(applicantWorkflow, maxLimitOrder);
        }

        for (ApplicantStageQualification qualification : previousQualifications)
        {
            if (!qualification.getQualificationStatus()
                .equals(QUALIFIED_AND_READONLY))
            {
                qualification.changeQualificationStatus(QUALIFIED_AND_READONLY);
                applicantWorkflowRepository.save(applicantWorkflow);
                applicantStageQualifiedEvents.add(new ApplicantStageQualifiedEvent(bootcampId
                    , qualification.getStage().getId()
                    , qualification.getQualificationStatus()
                    , applicantId, qualification.getStage().getStageName().getName()));
            }
        }
    }

    private ApplicantStageQualification getLastQualificationCreated(
        final ApplicantWorkflow applicantWorkflow)
    {
        Comparator<ApplicantStageQualification> orderComparator = Comparator
            .comparing(qualification -> qualification.getStage().getStageOrder().getOrder());
        return applicantWorkflow.getApplicantStageQualificationList().stream()
            .max(orderComparator)
            .orElse(null);
    }

    private List<ApplicantStageQualification> getPreviousQualifications(
        final ApplicantWorkflow applicantWorkflow, final int maxOrderLimit)
    {
        return applicantWorkflow.getApplicantStageQualificationList().stream()
            .filter(current -> current.getStage().getStageOrder().getOrder() <= maxOrderLimit)
            .collect(Collectors.toList());
    }
}
