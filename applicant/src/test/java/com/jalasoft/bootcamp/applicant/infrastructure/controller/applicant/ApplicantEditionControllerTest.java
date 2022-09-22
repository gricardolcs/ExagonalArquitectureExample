package com.jalasoft.bootcamp.applicant.infrastructure.controller.applicant;

import com.jalasoft.bootcamp.applicant.domain.applicant.Applicant;
import com.jalasoft.bootcamp.applicant.domain.applicant.Location;
import com.jalasoft.bootcamp.applicant.domain.applicant.CurriculumVitae;
import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantDTO;
import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.manipulation.ApplicantHandleUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ApplicantEditionControllerTest
{

    @Mock
    private ApplicantHandleUseCase applicantHandleUseCase;

    @InjectMocks
    private ApplicantController applicantController;

    private Applicant applicant;

    @BeforeEach
    void setUp()
    {
        applicant = new Applicant(
            UUID.randomUUID().getLeastSignificantBits(),
            "Maria Orellana",
            LocalDate.of(1995, Month.MARCH, 23),
            "Bachelor's degree",
            "maria.orellana@gmail.com",
            new Location("Bolivia", "Cochabamba"),
            "https://drive.google.com/open?id=...",
            "7628469",
            "System Engineering",
            Collections.emptyList(),
            Collections.emptySet(),
            Collections.emptySet(),
            Collections.emptyList(),
            new CurriculumVitae(
                new byte[0],
                "filenameCV.pdf",
                "Lorem java dolor mongo amet, .NET spring boot"), null);
    }

    @Test
    void testEditAnApplicantWithCorrectData()
    {
        long correctId = -23937812823L;
        ApplicantDTO applicantDTO = new ApplicantDTO(applicant);

        Mockito.when(applicantHandleUseCase.edit(correctId, applicantDTO)).thenReturn(applicant);
        ResponseEntity<ApplicantDTO> response = applicantController.edit(correctId, applicantDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testEditAnApplicantWithIncorrectData()
    {
        long incorrectId = -12345123122L;
        ApplicantDTO applicantEditionDTO = new ApplicantDTO(applicant);

        Mockito.when(applicantHandleUseCase.edit(incorrectId, applicantEditionDTO))
            .thenThrow(EntityNotFoundException.class);

        Assertions.assertThrows(
            EntityNotFoundException.class,
            () -> applicantController.edit(incorrectId, applicantEditionDTO));
    }
}
