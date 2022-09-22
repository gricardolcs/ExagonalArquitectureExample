package com.jalasoft.bootcamp.bootcamp.infrastructure.controller.applicantworkflow;

import static com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.QualificationStatus.PASSED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualification;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualificationAppliedDate;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualificationComment;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualificationSubmitDate;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield.NumericInputQualificationSchema;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield.QualificationField;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield.QualificationFieldSchema;
import com.jalasoft.bootcamp.becommon.domain.exceptions.InvalidArgumentsEntityException;
import com.jalasoft.bootcamp.bootcamp.domain.stage.Stage;
import com.jalasoft.bootcamp.bootcamp.domain.stage.StageName;
import com.jalasoft.bootcamp.bootcamp.domain.stage.StageOrder;
import com.jalasoft.bootcamp.bootcamp.domain.stage.schema.InputField;
import com.jalasoft.bootcamp.bootcamp.domain.stage.schema.InputFieldId;
import com.jalasoft.bootcamp.bootcamp.domain.stage.schema.NumericInput;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.applicantstagequalification.ApplicantStageQualificationCreationDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.applicantstagequalification.ApplicantStageQualificationDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.applicantworkflow.grading.ApplicantGradingInStageUseCase;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class ApplicantGradingInStageControllerTest
{
    private static long bootcampId;
    private static long applicantId;
    private static long stageId;
    @Mock
    private ApplicantGradingInStageUseCase applicantGradingInStageUseCase;
    @InjectMocks
    private ApplicantGradingInStageController applicantGradingInStageController;

    @BeforeAll
    static void setUp()
    {
        applicantId = 1L;
        bootcampId = 1L;
        stageId = 1L;
    }

    @Test
    public void testSuccessfulGradeApplicantStageQualification()
    {
        Long applicantStageQualificationId = 1L;
        Stage stage = new Stage(1L, new StageName("Initial"), new StageOrder(2), true
            , 1L, List.of(
            new InputField(new InputFieldId(1L), new NumericInput("CA:"), stageId))
            , false, false);

        List<QualificationField> qualifications = List
            .of(new QualificationField(1L
                , new NumericInputQualificationSchema("CA:", 2), applicantStageQualificationId));

        ApplicantStageQualification applicantStageQualification = new ApplicantStageQualification(
            applicantStageQualificationId,
            new ApplicantStageQualificationComment("Passed Successfully")
            , new ApplicantStageQualificationAppliedDate(LocalDate.of(2021, Month.JUNE, 20))
            , new ApplicantStageQualificationSubmitDate(
            LocalDateTime.of(
                LocalDate.of(2021, Month.DECEMBER, 20),
                LocalTime.of(13, 30))), stage, 1L, qualifications, PASSED);

        List<QualificationFieldSchema> validSchema = List
            .of(new NumericInputQualificationSchema("Score:", 10));

        ApplicantStageQualificationCreationDTO applicantStageQualificationCreationDTOValid =
            new ApplicantStageQualificationCreationDTO("to test",
                LocalDate.of(2021, Month.JUNE, 20),
                LocalDateTime.of(2021, Month.DECEMBER, 20, 15, 48),validSchema, PASSED, stageId);

        Mockito.when(
                applicantGradingInStageUseCase.gradeApplicantInStage(bootcampId, stageId,
                    applicantId,
                    applicantStageQualificationCreationDTOValid))
            .thenReturn(applicantStageQualification);

        ResponseEntity<ApplicantStageQualificationDTO> result =
            applicantGradingInStageController.gradeApplicant(bootcampId, stageId, applicantId,
                applicantStageQualificationCreationDTOValid);

        ApplicantStageQualificationDTO actualResponse = result.getBody();
        assertEquals(applicantId, actualResponse.getApplicantId());
        assertEquals(PASSED, actualResponse.getQualificationStatus());
        assertEquals(stageId, actualResponse.getStageId());
    }

    @Test
    public void testThrowsExceptionWhenGradeApplicantStageQualificationInvalidData()
    {
        List<QualificationFieldSchema> invalidSchema = List
            .of(new NumericInputQualificationSchema("Score:", -10));
        ApplicantStageQualificationCreationDTO applicantStageQualificationCreationDTOInvalid =
            new ApplicantStageQualificationCreationDTO("to test",
                LocalDate.of(2021, Month.JUNE, 20),
                LocalDateTime.of(2021, Month.DECEMBER, 20, 15, 48), invalidSchema, PASSED, stageId);

        Mockito.when(
                applicantGradingInStageUseCase.gradeApplicantInStage(bootcampId, stageId,
                    applicantId,
                    applicantStageQualificationCreationDTOInvalid))
            .thenThrow(InvalidArgumentsEntityException.class);

        assertThrows(InvalidArgumentsEntityException.class, () -> applicantGradingInStageController.
            gradeApplicant(bootcampId, stageId, applicantId,
                applicantStageQualificationCreationDTOInvalid));
    }
}
