package com.jalasoft.bootcamp.applicant.infrastructure.controller.applicant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import com.jalasoft.bootcamp.applicant.domain.applicant.Applicant;
import com.jalasoft.bootcamp.applicant.domain.applicant.CurriculumVitae;
import com.jalasoft.bootcamp.applicant.domain.applicant.Location;
import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantDTO;
import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.manipulation.ApplicantHandleUseCase;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class ApplicantCreationControllerTest
{
    @Mock
    private ApplicantHandleUseCase applicantHandleUseCase;

    @InjectMocks
    private ApplicantController applicantController;

    private Long applicantId;
    private String name;
    private LocalDate birthday;
    private String email;
    private String telephone;
    private String city;
    private String country;
    private String career;
    private String degree;
    private String photo;
    private byte[] curriculumVitae;
    private String fileNameCV;
    private String contentCV;

    @BeforeEach
    public void setUp()
    {
        applicantId = -998877665544L;
        name = "Luis Mercado";
        birthday = LocalDate.of(1995, Month.MARCH, 23);
        email = "luis.mercado@email.com";
        telephone = "7654268";
        city = "La paz";
        country = "Bolivia";
        career = "System Engineering";
        degree = "Master";
        photo = "Base64:asjknlasdnWWnmnCLlqweql";
        curriculumVitae = new byte[0];
        fileNameCV = "document.pdf";
        contentCV = "Lorem java dolor mongo amet, .NET spring boot";
    }

    @Test
    public void testAddSingleApplicantSuccessfully()
    {
        Applicant applicant = new Applicant(applicantId, name, birthday
            , degree, email, new Location(country, city)
            , photo, telephone
            , career, Collections.emptyList()
            , Collections.emptySet(), Collections.emptySet(), Collections.emptyList()
            , new CurriculumVitae(curriculumVitae, fileNameCV, contentCV), null);
        ApplicantDTO applicantDTO = new ApplicantDTO(applicant);

        Mockito.when(applicantHandleUseCase.create(any(ApplicantDTO.class)))
            .thenReturn(applicant);
        ResponseEntity<ApplicantDTO> actualResult =
            applicantController.create(applicantDTO);

        assertEquals(applicantId, actualResult.getBody().getId());
        assertEquals(name, actualResult.getBody().getFullName());
    }
}
