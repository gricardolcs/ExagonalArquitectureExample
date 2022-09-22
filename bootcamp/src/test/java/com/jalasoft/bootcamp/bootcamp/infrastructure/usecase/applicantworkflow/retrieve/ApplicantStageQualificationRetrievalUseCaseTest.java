package com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.applicantworkflow.retrieve;

import static com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.QualificationStatus.PASSED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualification;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualificationAppliedDate;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualificationComment;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualificationSubmitDate;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield.NumericInputQualificationSchema;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield.QualificationField;
import com.jalasoft.bootcamp.bootcamp.domain.applicantworkflow.ApplicantWorkflow;
import com.jalasoft.bootcamp.bootcamp.domain.applicantworkflow.ApplicantWorkflowRepository;
import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.Bootcamp;
import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.BootcampRepository;
import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.bootcamp.domain.stage.Stage;
import com.jalasoft.bootcamp.bootcamp.domain.stage.StageName;
import com.jalasoft.bootcamp.bootcamp.domain.stage.StageOrder;
import com.jalasoft.bootcamp.bootcamp.domain.stage.schema.InputField;
import com.jalasoft.bootcamp.bootcamp.domain.stage.schema.InputFieldId;
import com.jalasoft.bootcamp.bootcamp.domain.stage.schema.NumericInput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ApplicantStageQualificationRetrievalUseCaseTest
{

    @Mock
    private ApplicantWorkflowRepository applicantWorkflowRepository;

    @Mock
    private BootcampRepository bootcampRepository;

    @Mock
    private ApplicantStageQualificationRetrievalUseCase applicantStageQualificationRetrievalUseCase;

    private long bootcampId;

    private long applicantId;

    private long stageId;

    private Bootcamp bootcamp;

    private ApplicantWorkflow applicantWorkflow;

    @BeforeEach
    public void setUp()
    {
        bootcampId = 1L;
        applicantId = 1L;
        stageId = 1L;
        bootcamp = new Bootcamp(bootcampId,
            "Dev bootcamp"
            , "First Bolivian Bootcamp"
            , "Av. Melchor Perez #123"
            , LocalDate.of(2021, 7, 7)
            , LocalDate.of(2021, 12, 12)
            , 1, 1L, 1, 1L
            , 30, new HashSet<>(), 1
            , 1L, Collections.emptySet());

        Stage stage = new Stage(1L, new StageName("Psychometric"), new StageOrder(2),
            true, 1L,
            List.of(
                new InputField(new InputFieldId(1L), new NumericInput("CA:"), stageId),
                new InputField(new InputFieldId(2L), new NumericInput("Mixed Control"),
                    stageId))
            , false, false);
        Long applicantStageQualificationId = 1L;
        List<QualificationField> qualifications = List
            .of(new QualificationField(1L
                    , new NumericInputQualificationSchema("CA:", 1), applicantStageQualificationId)
                , new QualificationField(2L
                    , new NumericInputQualificationSchema("Mixed Control", 4),
                    applicantStageQualificationId));

        ApplicantStageQualification applicantStageQualification = new ApplicantStageQualification(
            applicantStageQualificationId
            , new ApplicantStageQualificationComment("Passed Successfully")
            , new ApplicantStageQualificationAppliedDate(LocalDate.of(2021, Month.JUNE, 20))
            , new ApplicantStageQualificationSubmitDate(
            LocalDateTime.of(
                LocalDate.of(2021, Month.DECEMBER, 20),
                LocalTime.of(17, 00))), stage, 1L, qualifications, PASSED);

        applicantWorkflow = new ApplicantWorkflow(UUID.randomUUID().getLeastSignificantBits()
            , bootcampId, applicantId, Set.of(applicantStageQualification));

        applicantStageQualificationRetrievalUseCase =
            new ApplicantStageQualificationRetrievalUseCase(
            applicantWorkflowRepository, bootcampRepository);
    }

    @Test
    public void testRetrieveApplicantStageQualificationWhenBootcampAndApplicantAndStageExist()
    {
        Mockito.when(bootcampRepository.findById(bootcampId))
            .thenReturn(Optional.of(bootcamp));

        Mockito.when(applicantWorkflowRepository
                .findByApplicantIdAndBootcampId(applicantId, bootcampId))
            .thenReturn(Optional.of(applicantWorkflow));

        ApplicantStageQualification actualResult = applicantStageQualificationRetrievalUseCase
            .retrieveApplicantStageQualificationByApplicantIdAndStageId(bootcampId, applicantId
                , stageId);

        assertEquals(PASSED, actualResult.getQualificationStatus());
        assertEquals("Passed Successfully", actualResult.getComment().getComment());
    }

    @Test
    public void testRetrieveApplicantStageQualificationExceptionWhenBootcampDoesNotExist()
    {
        Mockito.when(bootcampRepository.findById(bootcampId))
            .thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class
            , () -> applicantStageQualificationRetrievalUseCase
                .retrieveApplicantStageQualificationByApplicantIdAndStageId(bootcampId, applicantId
                    , stageId));

        assertSame(ErrorsUtil.ERROR_MESSAGE_ENTITY_NOT_FOUND, exception.getErrorMessage());
    }

    @Test
    public void testRetrieveApplicantStageQualificationExceptionWhenApplicantWorkflowDoesNotExist()
    {
        Mockito.when(bootcampRepository.findById(bootcampId))
            .thenReturn(Optional.of(bootcamp));

        Mockito.when(applicantWorkflowRepository.findByApplicantIdAndBootcampId(applicantId
            , bootcampId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
            EntityNotFoundException.class
            , () -> applicantStageQualificationRetrievalUseCase
                .retrieveApplicantStageQualificationByApplicantIdAndStageId(bootcampId, applicantId
                    , stageId));

        assertSame(ErrorsUtil.ERROR_MESSAGE_ENTITY_NOT_FOUND, exception.getErrorMessage());
    }

    @Test
    public void testRetrieveApplicantStageQualificationExceptionWhenStageDoesNotExist()
    {
        Mockito.when(bootcampRepository.findById(bootcampId))
            .thenReturn(Optional.of(bootcamp));

        Mockito.when(applicantWorkflowRepository.findByApplicantIdAndBootcampId(applicantId
            , bootcampId)).thenReturn(Optional.of(applicantWorkflow));

        long invalidStageId = 2L;

        EntityNotFoundException exception = assertThrows(
            EntityNotFoundException.class
            , () -> applicantStageQualificationRetrievalUseCase
                .retrieveApplicantStageQualificationByApplicantIdAndStageId(bootcampId, applicantId
                    , invalidStageId));

        assertSame(ErrorsUtil.ERROR_MESSAGE_ENTITY_NOT_FOUND, exception.getErrorMessage());
    }
}
