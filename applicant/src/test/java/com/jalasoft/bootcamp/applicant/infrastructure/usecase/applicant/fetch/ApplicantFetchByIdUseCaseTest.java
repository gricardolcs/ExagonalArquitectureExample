package com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.fetch;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jalasoft.bootcamp.applicant.domain.applicant.Applicant;
import com.jalasoft.bootcamp.applicant.domain.applicant.ApplicantRepository;
import com.jalasoft.bootcamp.applicant.domain.applicant.CurriculumVitae;
import com.jalasoft.bootcamp.applicant.domain.applicant.Location;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ApplicantFetchByIdUseCaseTest
{

    @Mock
    private ApplicantRepository applicantRepository;

    @InjectMocks
    private ApplicantFetchUseCase applicantFetchUseCase;

    private Applicant applicant;

    private long id;

    @BeforeEach
    public void setUp()
    {
        id = UUID.randomUUID().getLeastSignificantBits();
        applicant = new Applicant(
            id,
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
    void testFindApplicantById()
    {
        Optional<Applicant> applicantOptional = Optional.of(applicant);
        Mockito.when(applicantRepository.findById(id)).thenReturn(applicantOptional);
        applicantFetchUseCase.findById(id);

        Mockito.verify(applicantRepository).findById(id);
    }

    @Test
    void testApplicantNotFound()
    {
        Mockito.when(applicantRepository.findById(id)).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(
            EntityNotFoundException.class,
            () -> applicantFetchUseCase.findById(id));
        assertSame(ErrorsUtil.ERROR_MESSAGE_ENTITY_NOT_FOUND, exception.getErrorMessage());
    }
}
