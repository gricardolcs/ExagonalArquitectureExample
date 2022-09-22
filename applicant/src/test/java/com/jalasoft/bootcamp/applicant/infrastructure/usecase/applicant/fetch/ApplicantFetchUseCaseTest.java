package com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.fetch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.jalasoft.bootcamp.applicant.domain.applicant.Applicant;
import com.jalasoft.bootcamp.applicant.domain.applicant.ApplicantRepository;
import com.jalasoft.bootcamp.applicant.domain.applicant.CurriculumVitae;
import com.jalasoft.bootcamp.applicant.domain.applicant.Location;
import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantDTO;
import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.filter.ApplicantFilterUseCase;
import com.jalasoft.bootcamp.applicant.infrastructure.utils.Filter;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ApplicantFetchUseCaseTest
{
    @Mock
    private ApplicantRepository applicantRepository;

    @InjectMocks
    private ApplicantFetchUseCase applicantsFetchUseCase;

    @Mock
    private ApplicantFilterUseCase applicantFilterUseCase;

    private long id;
    private String name;
    private String email;
    private String telephone;
    private String photo;
    private LocalDate birthday;
    private String country;
    private String city;
    private String career;
    private String degree;
    private List<Long> applicantBootcampHistoryIds;
    private byte[] curriculumVitae;
    private String fileNameCV;
    private String contentCV;
    private List<String> skills;

    private final long bootcampId = UUID.randomUUID().getLeastSignificantBits();
    private final long applicantId = UUID.randomUUID().getLeastSignificantBits();
    private Applicant applicant;

    @BeforeEach
    void setUp()
    {
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
                "Lorem java dolor mongo amet, .NET spring boot"),
            Collections.emptyList());

        MockitoAnnotations.initMocks(this);
        id = UUID.randomUUID().getLeastSignificantBits();
        name = "Maria Orellana";
        email = "maria.orellana@gmail.com";
        telephone = "7628469";
        photo = "https://drive.google.com/open?id=...";
        birthday = LocalDate.of(1995, Month.MARCH, 23);
        country = "Bolivia";
        city = "Cochabamba";
        career = "System Engineering";
        degree = "Bachelor's degree";
        applicantBootcampHistoryIds = Collections.emptyList();
        curriculumVitae = new byte[0];
        fileNameCV = "document.pdf";
        contentCV = "Lorem java dolor mongo amet, .NET spring boot";
    }

    @Test
    void testFetchApplicants()
    {
        Applicant applicant = new Applicant(
            id,
            name,
            birthday,
            degree,
            email,
            new Location(country, city),
            photo,
            telephone,
            career,
            applicantBootcampHistoryIds,
            Collections.emptySet(),
            Collections.emptySet(),
            Collections.emptyList(),
            new CurriculumVitae(curriculumVitae, fileNameCV, contentCV), null);
        List<Applicant> expectedResult = new ArrayList<>();
        expectedResult.add(applicant);
        Mockito.when(applicantRepository.findAllByOrderByFullNameAsc())
            .thenReturn(expectedResult);
        List<ApplicantDTO> actualResult = applicantsFetchUseCase.fetchAll();
        assertEquals(expectedResult.size(), actualResult.size());
        assertEquals(
            expectedResult.get(0).getFullName(),
            actualResult.get(0).getFullName());
    }

    @Test
    void testFetchApplicantEmpty()
    {
        List<Applicant> expectedResult = new ArrayList<>();
        Mockito.when(applicantRepository.findAllByOrderByFullNameAsc())
            .thenReturn(expectedResult);
        List<ApplicantDTO> actualResult = applicantsFetchUseCase.fetchAll();
        assertEquals(expectedResult.size(), actualResult.size());
    }

    @Test
    void sanitizeFilter()
    {
        Filter filter = new Filter("FullName", Arrays.asList("Alb/ert10o*"));
        applicantsFetchUseCase.sanitizeFilter(filter);
        List<Applicant> resultFilterApplicants = List.of(applicant);
        when(applicantFilterUseCase.getApplicantsByFiltering(bootcampId, filter)).thenReturn(
            resultFilterApplicants);
        List<ApplicantDTO> applicants = applicantsFetchUseCase.findByFilters(bootcampId,
            Arrays.asList(filter));
        assertEquals(List.of(new ApplicantDTO(applicant)), applicants);
    }
}
