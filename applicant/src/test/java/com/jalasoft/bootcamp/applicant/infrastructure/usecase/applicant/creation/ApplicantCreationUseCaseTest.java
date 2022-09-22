package com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.creation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.jalasoft.bootcamp.applicant.domain.applicant.Applicant;
import com.jalasoft.bootcamp.applicant.domain.applicant.ApplicantRepository;
import com.jalasoft.bootcamp.applicant.domain.applicant.CurriculumVitae;
import com.jalasoft.bootcamp.applicant.domain.applicant.Location;
import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantDTO;
import com.jalasoft.bootcamp.becommon.infrastructure.factory.Factory;
import com.jalasoft.bootcamp.applicant.infrastructure.factory.applicant.ApplicantCreationFactory;
import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.manipulation.ApplicantHandleUseCase;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ApplicantCreationUseCaseTest
{

    @Mock
    private ApplicantRepository applicantRepository;

    @Mock
    private Factory<Applicant, ApplicantCreation> applicantFactory;

    @InjectMocks
    private ApplicantHandleUseCase applicantHandleUseCase;

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
    void setUp()
    {
        Factory<Applicant, ApplicantCreation> applicantFactory = new ApplicantCreationFactory();
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
    void testAddSingleApplicant()
    {
        Applicant applicant = new Applicant(applicantId, name,
            birthday, degree, email, new Location(country, city),
            photo, telephone,
            career, Collections.emptyList(),
            Collections.emptySet(), Collections.emptySet(), Collections.emptyList(),
            new CurriculumVitae(curriculumVitae, fileNameCV, contentCV), null);

        ApplicantDTO applicantDTO = new ApplicantDTO(applicant);

        when(applicantRepository.save(applicant)).thenReturn(applicant);
        when(applicantFactory.create(any(ApplicantCreation.class))).thenReturn(applicant);

        Applicant actualResult = applicantHandleUseCase.create(applicantDTO);
        assertEquals(actualResult.getFullName(), applicantDTO.getFullName());
        assertEquals(actualResult.getBirthday(), applicantDTO.getBirthday());
        assertEquals(actualResult.getEmail(), applicantDTO.getEmail());
        assertEquals(actualResult.getTelephone(), applicantDTO.getTelephone());
        assertEquals(actualResult.getLocation().getCity(), applicantDTO.getCity());
        assertEquals(actualResult.getLocation().getCountry(), applicantDTO.getCountry());
        assertEquals(actualResult.getCareer(), applicantDTO.getCareer());
        assertEquals(actualResult.getDegree(), applicantDTO.getDegree());
    }
}
