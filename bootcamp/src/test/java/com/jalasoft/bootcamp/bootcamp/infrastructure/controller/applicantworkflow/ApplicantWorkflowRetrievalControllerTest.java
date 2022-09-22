package com.jalasoft.bootcamp.bootcamp.infrastructure.controller.applicantworkflow;

import static com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.QualificationStatus.PASSED;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualification;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualificationAppliedDate;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualificationComment;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualificationSubmitDate;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield.NumericInputQualificationSchema;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield.QualificationField;
import com.jalasoft.bootcamp.bootcamp.domain.applicantworkflow.ApplicantWorkflow;
import com.jalasoft.bootcamp.bootcamp.domain.stage.Stage;
import com.jalasoft.bootcamp.bootcamp.domain.stage.StageName;
import com.jalasoft.bootcamp.bootcamp.domain.stage.StageOrder;
import com.jalasoft.bootcamp.bootcamp.domain.stage.schema.InputField;
import com.jalasoft.bootcamp.bootcamp.domain.stage.schema.InputFieldId;
import com.jalasoft.bootcamp.bootcamp.domain.stage.schema.NumericInput;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.applicantworkflow.ApplicantWorkflowDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.applicantworkflow.retrieve.ApplicantWorkflowRetrievalUseCase;
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
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ApplicantWorkflowRetrievalControllerTest {

    @Mock
    private ApplicantWorkflowRetrievalUseCase applicantWorkflowRetrievalUseCase;

    private ApplicantWorkflowRetrievalController applicantWorkflowRetrievalController;

    private long bootcampId;

    private long applicantId;

    private ApplicantWorkflow applicantWorkflow;

    @BeforeEach
    public void setUp() {
        bootcampId = 1L;
        applicantId = 1L;

        applicantWorkflow = new ApplicantWorkflow(UUID.randomUUID().getLeastSignificantBits()
            , bootcampId, applicantId, Collections.emptySet());
        applicantWorkflowRetrievalController = new ApplicantWorkflowRetrievalController(
            applicantWorkflowRetrievalUseCase);
    }

    @Test
    public void testFetchApplicantWorkflowWhenApplicantQualificationsIsEmpty() {
        Mockito.when(applicantWorkflowRetrievalUseCase
            .retrieveApplicantWorkflowByBootcampIdAndApplicantId(bootcampId, applicantId))
            .thenReturn(applicantWorkflow);

        ResponseEntity<ApplicantWorkflowDTO> result = applicantWorkflowRetrievalController
            .fetchApplicantWorkflow(bootcampId, applicantId);

        ApplicantWorkflowDTO actualBody = result.getBody();

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(1L, actualBody.getApplicantId());
        assertEquals(1L, actualBody.getBootcampId());
        assertEquals(Collections.emptyList(), actualBody.getApplicantQualifications());
    }

    @Test
    public void testFetchApplicantWorkflowWhenApplicantQualificationsHasOneQualification() {
        Stage stage = new Stage(1L, new StageName("Psychometric"), new StageOrder(2),
            true, 1L,
            List.of(new InputField(new InputFieldId(1L), new NumericInput("CA:"), 1L),
                new InputField(new InputFieldId(2L), new NumericInput("Mixed Control"), 1L))
            , false, false);

        Long applicantStageQualificationId = 1L;
        List<QualificationField> qualifications = List
            .of(new QualificationField(1L
                    , new NumericInputQualificationSchema("CA:", 1), applicantStageQualificationId)
                , new QualificationField(2L
                    , new NumericInputQualificationSchema("Mixed Controle:", 4),
                    applicantStageQualificationId));

        ApplicantStageQualification applicantStageQualification = new ApplicantStageQualification(
            applicantStageQualificationId
            , new ApplicantStageQualificationComment("Passed Successfully")
            , new ApplicantStageQualificationAppliedDate(LocalDate.of(2021, Month.JUNE, 20))
            , new ApplicantStageQualificationSubmitDate(
            LocalDateTime.of(LocalDate.of(2021, Month.DECEMBER, 20),
                LocalTime.of(13, 45)))
            , stage, 1L, qualifications, PASSED);

        ApplicantWorkflow applicantWorkflowWithQualifications = new ApplicantWorkflow(
            UUID.randomUUID().getLeastSignificantBits(), bootcampId, applicantId,
            Set.of(applicantStageQualification));
        applicantWorkflowRetrievalController = new ApplicantWorkflowRetrievalController(
            applicantWorkflowRetrievalUseCase);

        Mockito.when(applicantWorkflowRetrievalUseCase
            .retrieveApplicantWorkflowByBootcampIdAndApplicantId(bootcampId, applicantId))
            .thenReturn(applicantWorkflowWithQualifications);

        ResponseEntity<ApplicantWorkflowDTO> result = applicantWorkflowRetrievalController
            .fetchApplicantWorkflow(bootcampId, applicantId);

        ApplicantWorkflowDTO actualBody = result.getBody();

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(1L, actualBody.getApplicantId());
        assertEquals(1L, actualBody.getBootcampId());
        assertEquals(1, actualBody.getApplicantQualifications().size());
        assertEquals(PASSED,
            actualBody.getApplicantQualifications().stream().findFirst().get()
                .getQualificationStatus());
    }
}