package com.jalasoft.bootcamp.applicant.infrastructure.controller.applicant;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jalasoft.bootcamp.applicant.domain.applicant.Applicant;
import com.jalasoft.bootcamp.applicant.domain.applicant.CurriculumVitae;
import com.jalasoft.bootcamp.applicant.domain.applicant.Location;
import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantDTO;
import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.fetch.ApplicantFetchUseCase;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class FetchAllApplicantsControllerTest
{

    @Mock
    private ApplicantFetchUseCase applicantFetchUseCase;

    @InjectMocks
    private ApplicantController applicantController;

    private long id;
    private String fullName;
    private String email;
    private String telephone;
    private String photo;
    private LocalDate birthday;
    private String city;
    private String country;
    private String career;
    private String degree;
    private List<Long> applicantBootcampHistoryIds;
    private byte[] curriculumVitae;
    private String fileNameCV;
    private String contentCV;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.initMocks(this);
        id = UUID.randomUUID().getLeastSignificantBits();
        fullName = "Luis Mercado";
        email = "luis.mercado@email.com";
        telephone = "7654268";
        photo = "https://drive.google.com/open?id=...";
        birthday = LocalDate.of(1995, Month.MARCH, 23);
        city = "La paz";
        country = "Bolivia";
        career = "System Engineering";
        degree = "Bachelor's degree";
        applicantBootcampHistoryIds = Collections.emptyList();
        curriculumVitae = new byte[0];
        fileNameCV = "document.pdf";
        contentCV = "Lorem java dolor mongo amet, .NET spring boot";
    }

    @Test
    void testFetchAllApplicants()
    {
        Applicant applicant = new Applicant(
            id,
            fullName,
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
            new CurriculumVitae(curriculumVitae, fileNameCV,contentCV), null);
        List<ApplicantDTO> mockList = new ArrayList<>();
        mockList.add(new ApplicantDTO(applicant));

        Mockito.when(applicantFetchUseCase.fetchAll())
            .thenReturn(mockList);
        ResponseEntity<List<ApplicantDTO>> result = applicantController
            .getAllApplicants();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    void testFetchAllApplicantsEmpty()
    {
        List<ApplicantDTO> mockList = new ArrayList<>();

        Mockito.when(applicantFetchUseCase.fetchAll())
            .thenReturn(mockList);
        ResponseEntity<List<ApplicantDTO>> result = applicantController
            .getAllApplicants();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(200, result.getStatusCodeValue());
    }
}
