package com.jalasoft.bootcamp.bootcamp.infrastructure.controller.applicantworkflow;

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
import com.jalasoft.bootcamp.bootcamp.domain.stage.Stage;
import com.jalasoft.bootcamp.bootcamp.domain.stage.StageName;
import com.jalasoft.bootcamp.bootcamp.domain.stage.StageOrder;
import com.jalasoft.bootcamp.bootcamp.domain.stage.schema.InputField;
import com.jalasoft.bootcamp.bootcamp.domain.stage.schema.InputFieldId;
import com.jalasoft.bootcamp.bootcamp.domain.stage.schema.NumericInput;
import com.jalasoft.bootcamp.bootcamp.infrastructure.controller.bootcamp.ApplicantWorkflowsRetrievalController;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.applicantworkflow.ApplicantWorkflowDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.applicantworkflow.retrieve.ApplicantWorkflowsRetrievalUseCase;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class ApplicantWorkflowsRetrievalControllerTest {

    @Mock
    private ApplicantWorkflowsRetrievalUseCase applicantWorkflowsRetrievalUseCase;

    private ApplicantWorkflowsRetrievalController applicantWorkflowsRetrievalController;

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
                LocalTime.of(15, 30))), stage, 1L, qualifications, PASSED);
        applicantWorkflow = new ApplicantWorkflow(UUID.randomUUID().getLeastSignificantBits()
            , bootcampId, applicantId, Set.of(applicantStageQualification));
        applicantWorkflowsRetrievalController = new ApplicantWorkflowsRetrievalController(
            applicantWorkflowsRetrievalUseCase);
    }

    @Test
    void testValidApplicantWorkflowsEmpty() {
        Mockito.when(
            applicantWorkflowsRetrievalUseCase.fetchAllApplicantWorkflowByApplicantId(applicantId))
            .thenReturn(Collections.emptyList());
        ResponseEntity<List<ApplicantWorkflowDTO>> result = applicantWorkflowsRetrievalController
            .getApplicantWorkflowsByApplicantId(applicantId);
        List<ApplicantWorkflowDTO> actualBody = result.getBody();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(200, result.getStatusCodeValue());
        assertTrue(actualBody.isEmpty());
    }

    @Test
    void testValidRetrievalApplicantWorkflows() {
        Mockito.when(
            applicantWorkflowsRetrievalUseCase.fetchAllApplicantWorkflowByApplicantId(applicantId))
            .thenReturn(List.of(applicantWorkflow));
        ResponseEntity<List<ApplicantWorkflowDTO>> result = applicantWorkflowsRetrievalController
            .getApplicantWorkflowsByApplicantId(applicantId);
        List<ApplicantWorkflowDTO> expectedBody = ApplicantWorkflowDTO
            .convert(List.of(applicantWorkflow));
        List<ApplicantWorkflowDTO> actualBody = result.getBody();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(expectedBody.size(), actualBody.size());
        assertEquals(expectedBody.get(0).getApplicantId(), actualBody.get(0).getApplicantId());
        assertEquals(expectedBody.get(0).getBootcampId(), actualBody.get(0).getBootcampId());
        assertEquals(expectedBody.get(0).getApplicantQualifications().get(0).getStageId(),
            actualBody.get(0).getApplicantQualifications().get(0).getStageId());
        assertEquals(expectedBody.get(0).getApplicantQualifications().get(0).getStageName(),
            actualBody.get(0).getApplicantQualifications().get(0).getStageName());
        assertEquals(
            expectedBody.get(0).getApplicantQualifications().get(0).getQualificationStatus(),
            actualBody.get(0).getApplicantQualifications().get(0).getQualificationStatus());
        assertEquals(
            expectedBody.get(0).getApplicantQualifications().get(0).getQualificationFieldSchemas(),
            actualBody.get(0).getApplicantQualifications().get(0).getQualificationFieldSchemas());
    }
}