package com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.applicantworkflow.retrieve;

import static com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.QualificationStatus.PASSED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualification;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualificationAppliedDate;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualificationComment;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualificationSubmitDate;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield.NumericInputQualificationSchema;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield.QualificationField;
import com.jalasoft.bootcamp.bootcamp.domain.applicantworkflow.ApplicantWorkflow;
import com.jalasoft.bootcamp.bootcamp.domain.applicantworkflow.ApplicantWorkflowRepository;
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
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ApplicantWorkflowsRetrievalUseCaseTest {

    @Mock
    private ApplicantWorkflowRepository applicantWorkflowRepository;

    private ApplicantWorkflowsRetrievalUseCase applicantWorkflowsRetrievalUseCase;

    private long bootcampId;

    private long applicantId;

    private long stageId;

    private ApplicantWorkflow applicantWorkflow;

    @BeforeEach
    public void setUp() {
        bootcampId = 1L;
        applicantId = 1L;
        stageId = 1L;
        Stage stage = new Stage(1L, new StageName("Psychometric"), new StageOrder(2)
            , true, 1L
            , List.of(
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
            1L, new ApplicantStageQualificationComment("")
            , new ApplicantStageQualificationAppliedDate(LocalDate.of(2021, Month.JUNE, 20))
            , new ApplicantStageQualificationSubmitDate(
            LocalDateTime.of(LocalDate.of(2021, Month.DECEMBER, 20),
                LocalTime.of(20, 36)))
            , stage, 1L, qualifications, PASSED);
        applicantWorkflow = new ApplicantWorkflow(UUID.randomUUID().getLeastSignificantBits()
            , bootcampId, applicantId, Set.of(applicantStageQualification));

        this.applicantWorkflowsRetrievalUseCase = new ApplicantWorkflowsRetrievalUseCase(
            applicantWorkflowRepository);
    }

    @Test
    void testRetrieveApplicantWorkflowByApplicantId() {
        Mockito.when(applicantWorkflowRepository.findAllByApplicantId(applicantId))
            .thenReturn(List.of(applicantWorkflow));
        List<ApplicantWorkflow> actualResult = applicantWorkflowsRetrievalUseCase
            .fetchAllApplicantWorkflowByApplicantId(applicantId);

        List<ApplicantWorkflow> expectedResult = List.of(applicantWorkflow);

        assertEquals(expectedResult.size(), actualResult.size());
        assertEquals(expectedResult.get(0), actualResult.get(0));
    }

    @Test
    void testRetrieveApplicantWorkflowsEmptyResult() {
        Mockito.when(applicantWorkflowRepository.findAllByApplicantId(applicantId))
            .thenReturn(Collections.emptyList());
        List<ApplicantWorkflow> actualResult = applicantWorkflowsRetrievalUseCase
            .fetchAllApplicantWorkflowByApplicantId(applicantId);

        assertTrue(actualResult.isEmpty());
    }
}