package com.jalasoft.bootcamp.applicant.infrastructure.controller.applicant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.jalasoft.bootcamp.applicant.domain.applicant.Applicant;
import com.jalasoft.bootcamp.applicant.domain.applicant.CurriculumVitae;
import com.jalasoft.bootcamp.applicant.domain.applicant.Location;
import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantDTO;
import com.jalasoft.bootcamp.applicant.infrastructure.utils.Filter;
import com.jalasoft.bootcamp.becommon.domain.exceptions.InvalidQueryParamValueException;
import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.fetch.ApplicantFetchUseCase;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ApplicantFilterControllerTest
{

    @Mock
    private ApplicantFetchUseCase applicantFetchUseCase;

    @InjectMocks
    private ApplicantFilterController applicantFilterController;

    private Applicant applicant;
    private ApplicantDTO applicantDTO;
    private Filter filter;

    private Long bootcampId = UUID.randomUUID().getLeastSignificantBits();

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
        filter = new Filter("FullName", Arrays.asList("Maria Orellana"));

        applicantDTO = new ApplicantDTO(applicant);
    }

    @Test
    void testFilterApplicantWhenCriteriaQueryParamHasAlphabeticalCharacters_thenReturnStatusOk()
    {
        when(applicantFetchUseCase.findByFilters(bootcampId, Arrays.asList(filter))).thenReturn(Arrays.asList(applicantDTO));

        ResponseEntity<List<ApplicantDTO>> responseEntity = applicantFilterController
            .filterApplicant(bootcampId, Arrays.asList(filter));

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testFilterApplicantWhenCriteriaQueryParamHasSpecialCharacters_thenReturnInvalidQueryParamValueException()
    {
        Filter filterEspecialChar = new Filter("FullName", Arrays.asList("mar[*#"));

        when(applicantFetchUseCase.findByFilters(bootcampId, List.of(filterEspecialChar))).thenThrow(
            InvalidQueryParamValueException.class);

        assertThrows(InvalidQueryParamValueException.class, () -> applicantFilterController
            .filterApplicant(bootcampId, Arrays.asList(filterEspecialChar)));
    }
}