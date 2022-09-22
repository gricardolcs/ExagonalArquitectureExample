package com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.fetch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jalasoft.bootcamp.applicant.domain.applicant.Applicant;
import com.jalasoft.bootcamp.applicant.domain.applicant.ApplicantRepository;
import com.jalasoft.bootcamp.applicant.domain.applicant.CurriculumVitae;
import com.jalasoft.bootcamp.applicant.domain.applicant.Location;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

class FindAllApplicantsIncludedInBootcampUseCaseTest
{

    private final long BOOTCAMP_ID = -1122334455L;

    @Mock
    private ApplicantRepository applicantRepository;

    @InjectMocks
    private ApplicantFetchUseCase applicantFetchUseCase;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFetchAllApplicantsByBootcampSuccessfully()
    {
        Applicant applicant = new Applicant(
            -998877665544L,
            "Pablo Perez",
            LocalDate.of(2000, 1, 1),
            "Graduated!",
            "pablo.perez@gmail.com",
            new Location("", "Cochabamba"),
            "some-url.com",
            "59167676767",
            "System Engineering",
            Collections.emptyList(),
            Set.of(BOOTCAMP_ID),
            Collections.emptySet(),
            Collections.emptyList(),
            new CurriculumVitae(
                new byte[0],
                "filenameCV.pdf",
                "Lorem java dolor mongo amet, .NET spring boot"), null);
        PageRequest pageable = PageRequest.of(1, 20, Sort.by(Sort.Direction.ASC, "fullName"));
        Mockito.when(applicantRepository.findApplicantsByIncludedInBootcampIds(BOOTCAMP_ID, Sort.by(Sort.Direction.ASC, "fullName")))
            .thenReturn(List.of(applicant));

        Page<Applicant> actualResult = applicantFetchUseCase
            .fetchAllApplicantsIncludedInBootcamp(BOOTCAMP_ID, true, pageable);

        assertEquals("Pablo Perez", actualResult.getContent().get(0).getFullName());
        assertEquals(-998877665544L, actualResult.getContent().get(0).getId());
    }

    @Test
    public void testFetchAllApplicantsByBootcampEmptyList()
    {
        PageRequest pageable = PageRequest.of(1, 20);
        Page<Applicant> page = new PageImpl(Lists.emptyList());
        Mockito.when(applicantRepository.findApplicantsByIncludedInBootcampIds(BOOTCAMP_ID, pageable))
            .thenReturn(page);

        Page<Applicant> actualResult = applicantFetchUseCase
            .fetchAllApplicantsIncludedInBootcamp(BOOTCAMP_ID, false, pageable);

        assertEquals(Collections.emptyList(), actualResult.getContent());
    }
}