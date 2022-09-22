package com.jalasoft.bootcamp.applicant.infrastructure.controller.applicant;

import com.jalasoft.bootcamp.applicant.domain.applicant.Applicant;
import com.jalasoft.bootcamp.applicant.domain.applicant.Location;
import com.jalasoft.bootcamp.applicant.domain.applicant.CurriculumVitae;
import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantDTO;
import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.fetch.ApplicantFetchUseCase;
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
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FetchAllApplicantsIncludedInBootcampControllerTest
{
    private final long BOOTCAMP_ID = -11998822773L;
    @Mock
    private ApplicantFetchUseCase applicantFetchUseCase;
    @InjectMocks
    private ApplicantController applicantController;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFetchAllApplicantsSuccessfullyWhenBootcampHasApplicants()
    {
        Applicant applicant = new Applicant(
            -998877665544L,
            "Pablo Perez",
            LocalDate.of(2000, 1, 1),
            "Graduated!",
            "pablo.perez@gmail.com",
            new Location("Bolivia", "Cochabamba"),
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

        Page<Applicant> page = new PageImpl(List.of(applicant));
        PageRequest pageable = PageRequest.of(1, 20);

        Mockito.when(applicantFetchUseCase.fetchAllApplicantsIncludedInBootcamp(
            BOOTCAMP_ID, true, pageable))
            .thenReturn(page);

        ResponseEntity<Page<ApplicantDTO>> actualResult =
            applicantController.findAllApplicantsIncludedInBootcamp(BOOTCAMP_ID, true, pageable);

        Page<ApplicantDTO> actualResultBody = actualResult.getBody();

        assertEquals(200, actualResult.getStatusCodeValue());
        assertEquals("Pablo Perez", actualResultBody.getContent().get(0).getFullName());
        assertEquals(-998877665544L, actualResultBody.getContent().get(0).getId());
    }

    @Test
    public void testFetchAllApplicantsEmptySuccessfullyWhenBootcampDoesntHasAnyApplicants()
    {
        Page<Applicant> page = new PageImpl(Lists.emptyList());
        PageRequest pageable = PageRequest.of(1, 20);
        Mockito.when(applicantFetchUseCase.fetchAllApplicantsIncludedInBootcamp(
            BOOTCAMP_ID, false, pageable))
            .thenReturn(page);

        ResponseEntity<Page<ApplicantDTO>> actualResult =
            applicantController.findAllApplicantsIncludedInBootcamp(BOOTCAMP_ID, false, pageable);

        assertEquals(200, actualResult.getStatusCodeValue());
        assertEquals(Collections.emptyList(), actualResult.getBody().getContent());
    }
}