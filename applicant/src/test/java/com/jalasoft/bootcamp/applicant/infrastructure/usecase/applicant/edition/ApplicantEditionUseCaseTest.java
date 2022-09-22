package com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.edition;

import com.jalasoft.bootcamp.applicant.domain.applicant.ApplicantRepository;
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
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplicantEditionUseCaseTest
{

    @Mock
    private ApplicantRepository applicantRepository;

    @InjectMocks
    private ApplicantHandleUseCase applicantHandleUseCase;

    private Applicant applicant;

    @BeforeEach
    void setUp()
    {
        applicant = new Applicant(UUID.randomUUID().getLeastSignificantBits(),
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
    public void testEditAnApplicantWithCorrectData()
    {
        Applicant editedApplicant = applicant;
        long applicantId = editedApplicant.getId();
        editedApplicant.setLocation(new Location("Peru", "Arequipa"));
        ApplicantDTO applicantDTO = new ApplicantDTO(editedApplicant);

        when(applicantRepository.findById(applicantId)).thenReturn(Optional.of(applicant));
        when(applicantHandleUseCase.edit(applicantId, applicantDTO)).thenReturn(editedApplicant);
        Applicant expected = applicantHandleUseCase.edit(applicantId, applicantDTO);

        assertEquals(
            editedApplicant.getLocation().getCountry(),
            expected.getLocation().getCountry());
        assertEquals(editedApplicant.getLocation().getCity(), expected.getLocation().getCity());
    }

    @Test
    public void testEditApplicantWithApplicantIdIncorrect()
    {
        long incorrectId = -1234123123L;
        ApplicantDTO applicantDTO = new ApplicantDTO(applicant);

        when(applicantRepository.findById(incorrectId)).thenThrow(EntityNotFoundException.class);

        Assertions.assertThrows(
            EntityNotFoundException.class,
            () -> applicantHandleUseCase.edit(incorrectId, applicantDTO));
    }
}
