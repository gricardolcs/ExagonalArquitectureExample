package com.jalasoft.bootcamp.applicant.infrastructure.controller.applicant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.jalasoft.bootcamp.applicant.domain.applicant.Applicant;
import com.jalasoft.bootcamp.applicant.domain.applicant.Location;
import com.jalasoft.bootcamp.applicant.domain.applicant.CurriculumVitae;
import com.jalasoft.bootcamp.applicant.domain.applicant.Report;
import com.jalasoft.bootcamp.applicant.domain.applicantstagequalification.ApplicantStageQualification;
import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantDTO;
import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.fetch.ApplicantFetchUseCase;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class FetchAllApplicantExcludingABootcampApplicantsControllerTest
{

    @Mock
    private ApplicantFetchUseCase applicantFetchUseCase;

    @InjectMocks
    private ApplicantController applicantController;

    private Long bootcampId;
    private String photo;
    private LocalDate birthday;
    private String career;
    private String degree;
    private List<Long> applicantBootcampHistoryIds;
    private Set<Long> includedInBootcampIds;
    private Set<ApplicantStageQualification> applicantStageQualifications;
    private List<Report> reports;
    private byte[] curriculumVitae;
    private String fileNameCV;
    private String contentCV;

    @BeforeEach
    public void setUp()
    {
        bootcampId = -4L;
        photo = "https://drive.google.com/open?id=...";
        birthday = LocalDate.of(1995, Month.MARCH, 23);
        career = "System Engineering";
        degree = "Bachelor's degree";
        applicantBootcampHistoryIds = Collections.emptyList();
        includedInBootcampIds = new HashSet<>();
        applicantStageQualifications = Collections.emptySet();
        reports = Collections.emptyList();
        curriculumVitae = new byte[0];
        fileNameCV = "document.pdf";
        contentCV = "Lorem java dolor mongo amet, .NET spring boot";
    }

    @Test
    public void testFetchAllApplicantExcludingBootcampApplicants()
    {
        Applicant applicant = new Applicant(
            -1L,
            "Pedro Becerra",
            birthday,
            degree,
            "pedro.becerra@gmail.com",
            new Location("Colombia", "Cali"),
            photo,
            "77777777",
            career,
            applicantBootcampHistoryIds,
            includedInBootcampIds,
            applicantStageQualifications,
            reports,
            new CurriculumVitae(curriculumVitae, fileNameCV, contentCV), null);
        when(applicantFetchUseCase
            .getAllApplicantsExcludingBootcampApplicants(bootcampId, "",
                Sort.unsorted())).thenReturn(
            List.of(new ApplicantDTO(applicant)));
        ResponseEntity<List<ApplicantDTO>> actualResult = applicantController
            .getAllApplicantsExcludingABootcampApplicants("", Sort.unsorted(), bootcampId);
        List<ApplicantDTO> actualResultBody = actualResult.getBody();
        assertEquals(200, actualResult.getStatusCodeValue());
        assertEquals("Pedro Becerra", actualResultBody.get(0).getFullName());
        assertEquals("Colombia", actualResultBody.get(0).getCountry());
    }

    @Test
    public void testFetchAllApplicantExcludingBootcampApplicantsAndSortByAscendingLocation()
    {
        Applicant firstApplicant = new Applicant(
            -1L,
            "Pedro Becerra",
            birthday,
            degree,
            "pedro.becerra@gmail.com",
            new Location("Colombia", "Cali"),
            photo,
            "77777777",
            career,
            applicantBootcampHistoryIds,
            includedInBootcampIds,
            applicantStageQualifications,
            reports,
            new CurriculumVitae(curriculumVitae, fileNameCV, contentCV), null);
        Applicant secondApplicant = new Applicant(
            -2L,
            "Ana Zambrana",
            birthday,
            degree,
            "ana.zambrana@gmail.com",
            new Location("Bolivia", "Cochabamba"),
            photo,
            "77777717",
            career,
            applicantBootcampHistoryIds,
            includedInBootcampIds,
            applicantStageQualifications,
            reports,
            new CurriculumVitae(curriculumVitae, fileNameCV, contentCV), null);

        when(applicantFetchUseCase
            .getAllApplicantsExcludingBootcampApplicants(bootcampId, "",
                Sort.by("location.country,ASC"))).thenReturn(
            List.of(
                new ApplicantDTO(secondApplicant),
                new ApplicantDTO(firstApplicant)));
        ResponseEntity<List<ApplicantDTO>> actualResult = applicantController
            .getAllApplicantsExcludingABootcampApplicants("", Sort.by("location.country,ASC"),
                bootcampId);
        List<ApplicantDTO> actualResultBody = actualResult.getBody();
        assertEquals(200, actualResult.getStatusCodeValue());
        assertEquals("Bolivia", actualResultBody.get(0).getCountry());
        assertEquals("Colombia", actualResultBody.get(1).getCountry());
    }

    @Test
    public void testFetchAllApplicantExcludingBootcampApplicantsAndSortByDescendingLocation()
    {
        Applicant firstApplicant = new Applicant(
            -1L,
            "Pedro Becerra",
            birthday,
            degree,
            "pedro.becerra@gmail.com",
            new Location("Colombia", "Cali"),
            photo,
            "77777777",
            career,
            applicantBootcampHistoryIds,
            includedInBootcampIds,
            applicantStageQualifications,
            reports,
            new CurriculumVitae(curriculumVitae, fileNameCV, contentCV), null);
        Applicant secondApplicant = new Applicant(
            -2L,
            "Ana Zambrana",
            birthday,
            degree,
            "ana.zambrana@gmail.com",
            new Location("Bolivia", "Cochabamba"),
            photo,
            "77777717",
            career,
            applicantBootcampHistoryIds,
            includedInBootcampIds,
            applicantStageQualifications,
            reports,
            new CurriculumVitae(curriculumVitae, fileNameCV, contentCV), null);

        when(applicantFetchUseCase
            .getAllApplicantsExcludingBootcampApplicants(bootcampId, "",
                Sort.by("location.country,DESC"))).thenReturn(
            List.of(
                new ApplicantDTO(firstApplicant),
                new ApplicantDTO(secondApplicant)));
        ResponseEntity<List<ApplicantDTO>> actualResult = applicantController
            .getAllApplicantsExcludingABootcampApplicants("", Sort.by("location.country,DESC"),
                bootcampId);
        List<ApplicantDTO> actualResultBody = actualResult.getBody();
        assertEquals(200, actualResult.getStatusCodeValue());
        assertEquals("Colombia", actualResultBody.get(0).getCountry());
        assertEquals("Bolivia", actualResultBody.get(1).getCountry());
    }

    @Test
    public void testFetchAllApplicantExcludingBootcampApplicantsAndSortByCriteria()
    {
        Applicant firstApplicant = new Applicant(
            -1L,
            "Pedro Becerra",
            birthday,
            degree,
            "pedro.becerra@gmail.com",
            new Location("Colombia", "Cali"),
            photo,
            "77777777",
            career,
            applicantBootcampHistoryIds,
            includedInBootcampIds,
            applicantStageQualifications,
            reports,
            new CurriculumVitae(curriculumVitae, fileNameCV, contentCV), null);

        when(applicantFetchUseCase
            .getAllApplicantsExcludingBootcampApplicants(bootcampId, "B",
                Sort.unsorted())).thenReturn(
            List.of(new ApplicantDTO(firstApplicant)));
        ResponseEntity<List<ApplicantDTO>> actualResult = applicantController
            .getAllApplicantsExcludingABootcampApplicants("B", Sort.unsorted(),
                bootcampId);
        List<ApplicantDTO> actualResultBody = actualResult.getBody();
        assertEquals(200, actualResult.getStatusCodeValue());
        assertEquals("Colombia", actualResultBody.get(0).getCountry());
    }
}