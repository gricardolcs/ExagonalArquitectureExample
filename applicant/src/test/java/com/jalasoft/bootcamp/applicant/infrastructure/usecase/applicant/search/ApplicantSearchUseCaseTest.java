package com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.search;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import com.jalasoft.bootcamp.applicant.domain.applicant.Applicant;
import com.jalasoft.bootcamp.applicant.domain.applicant.CurriculumVitae;
import com.jalasoft.bootcamp.applicant.domain.applicant.Location;
import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantDTO;
import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.filter.ApplicantFilterUseCase;
import com.jalasoft.bootcamp.applicant.infrastructure.utils.Filter;
import com.jalasoft.bootcamp.becommon.domain.exceptions.InvalidQueryParamValueException;
import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.fetch.ApplicantFetchUseCase;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ApplicantSearchUseCaseTest
{

    private final long bootcampId = UUID.randomUUID().getLeastSignificantBits();
    private final long applicantId = UUID.randomUUID().getLeastSignificantBits();

    @Mock
    private ApplicantFilterUseCase applicantFilterUseCase;

    @InjectMocks
    private ApplicantFetchUseCase applicantFetchUseCase;

    private Applicant applicant;
    private Filter filter;

    List<String> skills = new ArrayList<>();

    @BeforeEach
    void setUp()
    {
        skills.add("java");
        skills.add("python");

        applicant = new Applicant(
            applicantId,
            "Maria Orellana",
            LocalDate.of(1995, Month.MARCH, 23),
            "Bachelor's degree",
            "maria.orellana@gmail.com",
            new Location("Bolivia", "Cochabamba"),
            "https://drive.google.com/open?id=...",
            "76284698",
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
    void testFindByFilterWhenValueFullNameIsAlphabetical_thenReturnApplicantList()
    {
        filter = new Filter("FullName", Arrays.asList("maria"));
        List<Applicant> resultFilterApplicants = List.of(applicant);

        when(applicantFilterUseCase.getApplicantsByFiltering(bootcampId, filter)).thenReturn(
            resultFilterApplicants);

        List<ApplicantDTO> applicants = applicantFetchUseCase.findByFilters(bootcampId,
            Arrays.asList(filter));

        assertEquals(List.of(new ApplicantDTO(applicant)), applicants);
    }


    @Test
    void testFindByFilterWhenValueFullNameIsAsterisk_thenReturnInvalidQueryParamValueException()
    {
        filter = new Filter("FullName", Arrays.asList("*"));

        when(applicantFilterUseCase.getApplicantsByFiltering(bootcampId, filter)).thenThrow(InvalidQueryParamValueException.class);
        Assertions.assertThrows(InvalidQueryParamValueException.class, () -> applicantFetchUseCase
            .findByFilters(bootcampId,Arrays.asList(filter)));
    }

    @Test
    void testFindByFilterWhenValueFullNameIsNumbers_thenReturnInvalidQueryParamValueException()
    {
        filter = new Filter("FullName", Arrays.asList("1234"));

        when(applicantFilterUseCase.getApplicantsByFiltering(bootcampId, filter)).thenThrow(InvalidQueryParamValueException.class);
        Assertions.assertThrows(InvalidQueryParamValueException.class, () -> applicantFetchUseCase
            .findByFilters(bootcampId,Arrays.asList(filter)));
    }


    @Test
    void testFindByFilerWhenValueIsEmail_thenReturnApplicantList()
    {
        filter = new Filter("email", Arrays.asList("mar"));
        List<Applicant> resultFilterApplicants = List.of(applicant);

        when(applicantFilterUseCase.getApplicantsByFiltering(bootcampId, filter)).thenReturn(
            resultFilterApplicants);

        List<ApplicantDTO> applicants = applicantFetchUseCase.findByFilters(bootcampId,
            Arrays.asList(filter));

        assertEquals(List.of(new ApplicantDTO(applicant)), applicants);
    }

    @Test
    void testFindByFilterWhenValueIsDegree_thenReturnApplicantList()
    {
        filter = new Filter("degree", Arrays.asList("bachelor"));
        List<Applicant> resultFilterApplicants = List.of(applicant);

        when(applicantFilterUseCase.getApplicantsByFiltering(bootcampId, filter)).thenReturn(
            resultFilterApplicants);

        List<ApplicantDTO> applicants = applicantFetchUseCase.findByFilters(bootcampId,
            Arrays.asList(filter));

        assertEquals(List.of(new ApplicantDTO(applicant)), applicants);
    }

    @Test
    void testFindByFilterWhenValueIsSkill_thenReturnApplicantList()
    {

        filter = new Filter("skill", Arrays.asList("java"));
        List<Applicant> resultFilterApplicants = List.of(applicant);

        when(applicantFilterUseCase.getApplicantsByFiltering(bootcampId, filter)).thenReturn(
            resultFilterApplicants);

        List<ApplicantDTO> applicants = applicantFetchUseCase.findByFilters(bootcampId,
            Arrays.asList(filter));

        assertEquals(List.of(new ApplicantDTO(applicant)), applicants);
    }
}