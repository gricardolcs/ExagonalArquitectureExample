package com.jalasoft.bootcamp.bootcamp.infrastructure.controller.applicantworkflow;

import static com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.QualificationStatus.PASSED;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualification;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualificationAppliedDate;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualificationComment;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualificationSubmitDate;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield.NumericInputQualificationSchema;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield.QualificationField;
import com.jalasoft.bootcamp.bootcamp.domain.stage.Stage;
import com.jalasoft.bootcamp.bootcamp.domain.stage.StageName;
import com.jalasoft.bootcamp.bootcamp.domain.stage.StageOrder;
import com.jalasoft.bootcamp.bootcamp.domain.stage.schema.InputField;
import com.jalasoft.bootcamp.bootcamp.domain.stage.schema.InputFieldId;
import com.jalasoft.bootcamp.bootcamp.domain.stage.schema.NumericInput;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.applicantworkflow.ApplicantQualificationDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.applicantworkflow.retrieve.ApplicantStageQualificationRetrievalUseCase;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ApplicantStageQualificationRetrievalControllerTest {

    @Mock
    private ApplicantStageQualificationRetrievalUseCase applicantStageQualificationRetrievalUseCase;

    private ApplicantStageQualificationRetrievalController applicantStageQualificationRetrievalController;

    private long bootcampId;

    private long applicantId;

    private long stageId;

    private ApplicantStageQualification applicantStageQualification;

    @BeforeEach
    public void setUp() {
        applicantId = 1L;
        bootcampId = 1L;
        stageId = 1L;
        Long applicantStageQualificationId = 1L;
        Stage stage = new Stage(1L, new StageName("Psychometric"), new StageOrder(2)
            , true, 1L, List.of(
                new InputField(new InputFieldId(1L), new NumericInput("CA:"), stageId),
                new InputField(new InputFieldId(2L), new NumericInput("Mixed Control"), stageId))
            , false, false);

        List<QualificationField> qualifications = List
            .of(new QualificationField(1L
                    , new NumericInputQualificationSchema("CA:", 2), applicantStageQualificationId)
                , new QualificationField(2L
                    , new NumericInputQualificationSchema("Mixed Control:", 1),
                    applicantStageQualificationId));

        applicantStageQualification = new ApplicantStageQualification(applicantStageQualificationId,
            new ApplicantStageQualificationComment("Passed Successfully")
            , new ApplicantStageQualificationAppliedDate(LocalDate.of(2021, Month.JUNE, 20))
            , new ApplicantStageQualificationSubmitDate(
            LocalDateTime.of(LocalDate.of(2021, Month.DECEMBER, 20),
                LocalTime.of(13, 30))), stage, 1L, qualifications, PASSED);

        this.applicantStageQualificationRetrievalController =
            new ApplicantStageQualificationRetrievalController(
                applicantStageQualificationRetrievalUseCase);
    }

    @Test
    public void testFetchApplicantStageQualificationWhenStageQualificationExist() {
        Mockito.when(applicantStageQualificationRetrievalUseCase
            .retrieveApplicantStageQualificationByApplicantIdAndStageId(bootcampId, applicantId
                , stageId)).thenReturn(applicantStageQualification);

        ResponseEntity<ApplicantQualificationDTO> result =
            applicantStageQualificationRetrievalController.fetchApplicantStageQualification(
                bootcampId, applicantId, stageId);

        ApplicantQualificationDTO actualBody = result.getBody();

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(1L, actualBody.getStageId());
        assertEquals(PASSED, actualBody.getQualificationStatus());
        assertEquals("Psychometric", actualBody.getStageName());
        assertEquals("Passed Successfully", actualBody.getComment());
    }
}