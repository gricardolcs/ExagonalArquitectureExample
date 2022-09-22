package com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.bootcamp.edition;

import static com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.QualificationStatus.IN_PROGRESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualification;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualificationAppliedDate;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualificationComment;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualificationSubmitDate;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield.NumericInputQualificationSchema;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield.QualificationField;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield.QualificationFieldSchema;
import com.jalasoft.bootcamp.bootcamp.domain.applicantworkflow.ApplicantWorkflow;
import com.jalasoft.bootcamp.bootcamp.domain.applicantworkflow.ApplicantWorkflowRepository;
import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.Bootcamp;
import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.BootcampRepository;
import com.jalasoft.bootcamp.bootcamp.domain.department.Department;
import com.jalasoft.bootcamp.bootcamp.domain.department.DepartmentRepository;
import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.bootcamp.domain.projecttype.ProjectType;
import com.jalasoft.bootcamp.bootcamp.domain.projecttype.ProjectTypeName;
import com.jalasoft.bootcamp.bootcamp.domain.projecttype.ProjectTypeRepository;
import com.jalasoft.bootcamp.bootcamp.domain.stage.Stage;
import com.jalasoft.bootcamp.bootcamp.domain.stage.StageName;
import com.jalasoft.bootcamp.bootcamp.domain.stage.StageOrder;
import com.jalasoft.bootcamp.bootcamp.domain.stage.schema.InputField;
import com.jalasoft.bootcamp.bootcamp.domain.stage.schema.InputFieldId;
import com.jalasoft.bootcamp.bootcamp.domain.stage.schema.NumericInput;
import com.jalasoft.bootcamp.bootcamp.domain.workflow.Workflow;
import com.jalasoft.bootcamp.bootcamp.domain.workflow.WorkflowName;
import com.jalasoft.bootcamp.bootcamp.domain.workflow.WorkflowRepository;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.bootcamp.BootcampApplicantAssociationDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.eventpublisher.EventPublisher;
import com.jalasoft.bootcamp.bootcamp.infrastructure.factory.applicantstagequalification.ApplicantStageQualificationFactory;
import com.jalasoft.bootcamp.bootcamp.infrastructure.factory.applicantworkflow.ApplicantWorkflowFactory;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.applicantstagequalification.ApplicantStageQualificationCreation;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.applicantworkflow.grading.ApplicantWorkflowCreation;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.bootcamp.manipulation.BootcampApplicantAssociationUseCase;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BootcampApplicantAssociationUseCaseTest
{

    public static final Long WORKFLOW_SCHEMA_ID = -3L;
    public static final Long DEPARTMENT_SCHEMA_ID = -3L;
    public static final Long PROJECT_TYPE_SCHEMA_ID = -3L;
    private static final Long BOOTCAMP_ID = -5L;
    @Mock
    private BootcampRepository bootcampRepository;

    @Mock
    private WorkflowRepository workflowRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private ProjectTypeRepository projectTypeRepository;

    @Mock
    private ApplicantWorkflowRepository applicantWorkflowRepository;

    @Spy
    private final ApplicantWorkflowFactory applicantWorkflowFactory =
        new ApplicantWorkflowFactory();

    @Spy
    private final ApplicantStageQualificationFactory applicantStageQualificationFactory =
        new ApplicantStageQualificationFactory();

    @Mock
    private EventPublisher eventPublisher;

    private BootcampApplicantAssociationUseCase useCase;

    private Bootcamp bootcamp;

    @BeforeEach
    public void setUp()
    {
        useCase = new BootcampApplicantAssociationUseCase(bootcampRepository, workflowRepository
            , departmentRepository, projectTypeRepository, applicantWorkflowRepository
            , applicantWorkflowFactory, applicantStageQualificationFactory
            , eventPublisher);
        bootcamp = new Bootcamp(BOOTCAMP_ID
            , "Bolivia Bootcamp"
            , "Tech Elevator offers a coding bootcamp dedicated "
            + "to readying students for a career as a software developer."
            , "Av. Melchor Perez #123"
            , LocalDate.of(2021, 03, 16)
            , LocalDate.of(2021, 03, 31)
            , 1, WORKFLOW_SCHEMA_ID, 1, DEPARTMENT_SCHEMA_ID,
            30, new HashSet<>()
            , 1, PROJECT_TYPE_SCHEMA_ID, new HashSet<>());
    }

    @Test
    public void testAssociateApplicantsToBootcamp()
    {
        Long firstApplicantId = -1L;
        Long secondApplicantId = -2L;
        Set<Long> applicantIds = Set.of(firstApplicantId, secondApplicantId);
        Stage stage = new Stage(-5L, new StageName("Initial"), new StageOrder(1)
            , true, WORKFLOW_SCHEMA_ID,
            List.of(
                new InputField(new InputFieldId(2L), new NumericInput("Score:"), -5L))
            , false, false);
        Long applicantStageQualificationId = 1L;
        List<QualificationField> qualifications = List
            .of(new QualificationField(1L
                , new NumericInputQualificationSchema("Score:", null)
                , applicantStageQualificationId));
        Long firstApplicantWorkflowId = UUID.randomUUID().getLeastSignificantBits();
        Long secondApplicantWorkflowId = UUID.randomUUID().getLeastSignificantBits();
        ApplicantStageQualification firstApplicantStageQualification =
            new ApplicantStageQualification(
            applicantStageQualificationId, new ApplicantStageQualificationComment("")
            , new ApplicantStageQualificationAppliedDate(LocalDate.now())
            , new ApplicantStageQualificationSubmitDate(null)
            , stage, firstApplicantWorkflowId, qualifications, IN_PROGRESS);
        ApplicantStageQualification secondApplicantStageQualification =
            new ApplicantStageQualification(
            applicantStageQualificationId, new ApplicantStageQualificationComment("")
            , new ApplicantStageQualificationAppliedDate(LocalDate.now())
            , new ApplicantStageQualificationSubmitDate(null)
            , stage, secondApplicantWorkflowId, qualifications, IN_PROGRESS);
        Workflow workflow = new Workflow(WORKFLOW_SCHEMA_ID
            , new WorkflowName("Development workflow"), List.of(stage));
        Department department = new Department(DEPARTMENT_SCHEMA_ID
            , "QA", "This is the description de QA department");
        ProjectType projectType = new ProjectType(PROJECT_TYPE_SCHEMA_ID
            , new ProjectTypeName("Bootcamp"));
        List<QualificationFieldSchema> qualificationFieldSchemas = List
            .of(new NumericInputQualificationSchema("Score:", null));

        ApplicantWorkflow firstApplicantWorkflow = new ApplicantWorkflow(firstApplicantWorkflowId
            , BOOTCAMP_ID, firstApplicantId, new HashSet<>());
        ApplicantWorkflow secondApplicantWorkflow = new ApplicantWorkflow(secondApplicantWorkflowId
            , BOOTCAMP_ID, secondApplicantId, new HashSet<>());

        when(bootcampRepository.findById(BOOTCAMP_ID)).thenReturn(Optional.of(bootcamp));
        when(workflowRepository.findById(WORKFLOW_SCHEMA_ID)).thenReturn(Optional.of(workflow));
        when(departmentRepository.findById(DEPARTMENT_SCHEMA_ID))
            .thenReturn(Optional.of(department));
        when(projectTypeRepository.findById(PROJECT_TYPE_SCHEMA_ID))
            .thenReturn(Optional.of(projectType));

        when(applicantWorkflowRepository.save(firstApplicantWorkflow))
            .thenReturn(firstApplicantWorkflow);
        when(applicantWorkflowRepository.save(secondApplicantWorkflow))
            .thenReturn(secondApplicantWorkflow);

        when(applicantWorkflowFactory
            .create(new ApplicantWorkflowCreation(firstApplicantId, bootcamp)))
            .thenReturn(firstApplicantWorkflow);
        when(applicantWorkflowFactory
            .create(new ApplicantWorkflowCreation(secondApplicantId, bootcamp)))
            .thenReturn(secondApplicantWorkflow);

        when(applicantStageQualificationFactory.create(new ApplicantStageQualificationCreation(
            firstApplicantWorkflowId, stage, "", LocalDate.now(), null
            , qualificationFieldSchemas, IN_PROGRESS)))
            .thenReturn(firstApplicantStageQualification);
        when(applicantStageQualificationFactory.create(new ApplicantStageQualificationCreation(
            secondApplicantWorkflowId, stage, "", LocalDate.now(), null
            , qualificationFieldSchemas, IN_PROGRESS)))
            .thenReturn(secondApplicantStageQualification);

        Bootcamp bootcamp = useCase
            .associate(new BootcampApplicantAssociationDTO(BOOTCAMP_ID, applicantIds));
        Set<Long> expected = Set.of(firstApplicantId, secondApplicantId);
        Set<Long> actual = bootcamp.getBootcampMembers();
        assertEquals(expected, actual);
    }

    @Test
    public void testAssociateToBootcampWithApplicantsExistent()
    {
        Long thirdApplicantId = -3L;
        Long fourthApplicantId = -4L;
        bootcamp.addMembers(Set.of(-1L, -2L));
        Stage stage = new Stage(-5L, new StageName("Initial"), new StageOrder(1)
            , true, WORKFLOW_SCHEMA_ID,
            List.of(
                new InputField(new InputFieldId(1L), new NumericInput("Score:"), -5L))
            , false, false);
        Workflow workflow = new Workflow(WORKFLOW_SCHEMA_ID
            , new WorkflowName("Development workflow"), List.of(stage));
        Department department = new Department(DEPARTMENT_SCHEMA_ID
            , "QA", "This is the description de QA department");
        ProjectType projectType = new ProjectType(PROJECT_TYPE_SCHEMA_ID
            , new ProjectTypeName("Bootcamp"));
        when(bootcampRepository.findById(BOOTCAMP_ID)).thenReturn(Optional.of(bootcamp));
        when(workflowRepository.findById(WORKFLOW_SCHEMA_ID))
            .thenReturn(Optional.of(workflow));
        when(departmentRepository.findById(DEPARTMENT_SCHEMA_ID))
            .thenReturn(Optional.of(department));
        when(projectTypeRepository.findById(PROJECT_TYPE_SCHEMA_ID))
            .thenReturn(Optional.of(projectType));

        Long applicantStageQualificationId = 1L;
        List<QualificationField> qualifications = List
            .of(new QualificationField(1L
                , new NumericInputQualificationSchema("Score:", null)
                , applicantStageQualificationId));
        Long firstApplicantWorkflowId = UUID.randomUUID().getLeastSignificantBits();
        Long secondApplicantWorkflowId = UUID.randomUUID().getLeastSignificantBits();
        ApplicantStageQualification thirdApplicantStageQualification =
            new ApplicantStageQualification(
            applicantStageQualificationId, new ApplicantStageQualificationComment("")
            , new ApplicantStageQualificationAppliedDate(LocalDate.now())
            , new ApplicantStageQualificationSubmitDate(null)
            , stage, firstApplicantWorkflowId, qualifications, IN_PROGRESS);
        ApplicantStageQualification fourthApplicantStageQualification =
            new ApplicantStageQualification(
            applicantStageQualificationId, new ApplicantStageQualificationComment("")
            , new ApplicantStageQualificationAppliedDate(LocalDate.now())
            , new ApplicantStageQualificationSubmitDate(null)
            , stage, secondApplicantWorkflowId, qualifications, IN_PROGRESS);

        ApplicantWorkflow thirdApplicantWorkflow = new ApplicantWorkflow(firstApplicantWorkflowId
            , BOOTCAMP_ID, thirdApplicantId, new HashSet<>());
        ApplicantWorkflow fourthApplicantWorkflow = new ApplicantWorkflow(secondApplicantWorkflowId
            , BOOTCAMP_ID, fourthApplicantId, new HashSet<>());
        List<QualificationFieldSchema> qualificationFieldSchemas = List
            .of(new NumericInputQualificationSchema("Score:", null));

        when(applicantWorkflowRepository.save(thirdApplicantWorkflow))
            .thenReturn(thirdApplicantWorkflow);
        when(applicantWorkflowRepository.save(fourthApplicantWorkflow))
            .thenReturn(fourthApplicantWorkflow);

        when(applicantWorkflowFactory.create(new ApplicantWorkflowCreation(thirdApplicantId
            , bootcamp))).thenReturn(thirdApplicantWorkflow);
        when(applicantWorkflowFactory.create(new ApplicantWorkflowCreation(fourthApplicantId
            , bootcamp))).thenReturn(fourthApplicantWorkflow);

        when(applicantStageQualificationFactory.create(new ApplicantStageQualificationCreation(
            firstApplicantWorkflowId, stage, "", LocalDate.now(), null
            , qualificationFieldSchemas, IN_PROGRESS)))
            .thenReturn(thirdApplicantStageQualification);
        when(applicantStageQualificationFactory.create(new ApplicantStageQualificationCreation(
            secondApplicantWorkflowId, stage, "", LocalDate.now(), null
            , qualificationFieldSchemas, IN_PROGRESS)))
            .thenReturn(fourthApplicantStageQualification);

        Set<Long> applicantIds = Set.of(-1L, -2L, -3L, -4L);
        Bootcamp actual = useCase
            .associate(new BootcampApplicantAssociationDTO(BOOTCAMP_ID
                , applicantIds));
        Set<Long> expected = Set.of(-1L, -2L, -3L, -4L);
        assertEquals(expected, actual.getBootcampMembers());
    }

    @Test
    public void testAssociateWithAInvalidBootcamp()
    {
        Set<Long> applicantIds = Set.of(-1L, -2L);
        when(bootcampRepository.findById(BOOTCAMP_ID)).thenThrow(EntityNotFoundException.class);
        Assertions.assertThrows(
            EntityNotFoundException.class,
            () -> useCase.associate(new BootcampApplicantAssociationDTO(BOOTCAMP_ID
                , applicantIds)));
    }
}
