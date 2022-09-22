package com.jalasoft.bootcamp.applicant.infrastructure.controller.applicant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantRegisteredAtBootcampsDTO;
import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantRegisteredInBootcampDTO;
import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.update.RegisterApplicantUseCase;
import com.jalasoft.bootcamp.becommon.domain.exceptions.DomainExceptionHandler;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class RegisterApplicantsControllerTest
{

    @Mock
    private RegisterApplicantUseCase registerApplicantUseCase;

    @Mock
    private DomainExceptionHandler domainExceptionHandler;

    @InjectMocks
    private RegisterApplicantsController registerApplicantsController;

    private Long bootcampId;
    private Long applicantId;

    @BeforeEach
    public void setUp() {
        bootcampId = -4L;
        applicantId = -5L;
    }

    @Test
    public void testRegisterExistingApplicantsInBootcamp() {
        List<Long> applicantIds = List.of(-1L);
        ApplicantRegisteredInBootcampDTO applicantRegisteredInBootcampDTO = new ApplicantRegisteredInBootcampDTO(
            Set.of(-1L));
        when(registerApplicantUseCase
            .registerApplicantsInBootcamp(applicantIds, bootcampId))
            .thenReturn(applicantRegisteredInBootcampDTO);
        ResponseEntity<ApplicantRegisteredInBootcampDTO> actualResult = registerApplicantsController
            .registerApplicantsInBootcamp(bootcampId, List.of(-1L));
        ApplicantRegisteredInBootcampDTO actualResultBody = actualResult.getBody();
        assertEquals(HttpStatus.OK, actualResult.getStatusCode());
        assertTrue(actualResultBody.getRegisteredApplicants().stream().anyMatch(applicantId ->  applicantId.equals("-1")));
    }

    @Test
    public void testRegisterApplicantInBootcamps() {
        List<Long> bootcampIds = List.of(-1L, -2L);
        ApplicantRegisteredAtBootcampsDTO applicantRegisteredAtBootcampsDTO = new ApplicantRegisteredAtBootcampsDTO(
            Set.of(-1L, -2L));
        when(registerApplicantUseCase
            .registerApplicantInBootcamps(applicantId, bootcampIds))
            .thenReturn(applicantRegisteredAtBootcampsDTO);
        ResponseEntity<ApplicantRegisteredAtBootcampsDTO> actualResult = registerApplicantsController
            .registerApplicantAtBootcamps(applicantId, List.of(-1L, -2L));
        ApplicantRegisteredAtBootcampsDTO actualResultBody = actualResult.getBody();
        assertEquals(HttpStatus.OK, actualResult.getStatusCode());
        assertTrue(actualResultBody.getApplicantRegisteredAtBootcamps().stream().anyMatch(applicantId ->  applicantId.equals("-1")));
        assertTrue(actualResultBody.getApplicantRegisteredAtBootcamps().stream().anyMatch(applicantId ->  applicantId.equals("-2")));
        assertFalse(actualResultBody.getApplicantRegisteredAtBootcamps().stream().anyMatch(applicantId ->  applicantId.equals("-3")));
    }
}