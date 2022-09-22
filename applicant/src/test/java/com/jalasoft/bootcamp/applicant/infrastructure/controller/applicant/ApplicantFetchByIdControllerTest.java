package com.jalasoft.bootcamp.applicant.infrastructure.controller.applicant;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jalasoft.bootcamp.applicant.domain.applicant.Applicant;
import com.jalasoft.bootcamp.applicant.domain.applicant.CurriculumVitae;
import com.jalasoft.bootcamp.applicant.domain.applicant.Location;

import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantDTO;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.fetch.ApplicantFetchUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class ApplicantFetchByIdControllerTest
{
    @Mock
    private ApplicantFetchUseCase applicantFetchUseCase;

    @InjectMocks
    private ApplicantController applicantController;

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

    @BeforeEach
    void setUp()
    {
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
    void testFindApplicantById()
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

        Mockito.when(applicantFetchUseCase.findById(id)).thenReturn(applicant);
        ResponseEntity<ApplicantDTO> responseEntity = applicantController
            .findById(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }
}
