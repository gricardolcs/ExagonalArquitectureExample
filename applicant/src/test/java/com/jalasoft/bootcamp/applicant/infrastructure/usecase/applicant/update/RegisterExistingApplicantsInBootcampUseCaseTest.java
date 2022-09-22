package com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.update;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.jalasoft.bootcamp.applicant.domain.applicant.Applicant;
import com.jalasoft.bootcamp.applicant.domain.applicant.ApplicantRepository;
import com.jalasoft.bootcamp.applicant.domain.applicant.CurriculumVitae;
import com.jalasoft.bootcamp.applicant.domain.applicant.Location;
import com.jalasoft.bootcamp.applicant.domain.applicant.Report;
import com.jalasoft.bootcamp.applicant.domain.applicantstagequalification.ApplicantStageQualification;
import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantRegisteredAtBootcampsDTO;
import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantRegisteredInBootcampDTO;
import com.jalasoft.bootcamp.applicant.infrastructure.eventpublisher.EventPublisher;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class RegisterApplicantUseCaseTest
{

    public static final String BOOTCAMP_SERVICE_URL = "http://localhost:9094/bootcamps/";

    @Mock
    private ApplicantRepository applicantRepository;

    @Mock
    private EventPublisher eventPublisher;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private RegisterApplicantUseCase registerApplicantUseCase;

    private Long bootcampId;
    private Long applicantId;
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
    private Set<Long> includedInBootcampIds;
    private Set<ApplicantStageQualification> applicantStageQualifications;
    private List<Report> reports;
    private byte[] curriculumVitae;
    private String fileNameCV;
    private String contentCV;

    @BeforeEach
    public void setUp()
    {
        ReflectionTestUtils.setField(registerApplicantUseCase
            , "BOOTCAMP_SERVICE_URL", BOOTCAMP_SERVICE_URL);
        bootcampId = -4L;
        applicantId = -2L;
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
        includedInBootcampIds = new HashSet<>();
        applicantStageQualifications = Collections.emptySet();
        reports = Collections.emptyList();
        curriculumVitae = new byte[0];
        fileNameCV = "document.pdf";
        contentCV = "Lorem java dolor mongo amet, .NET spring boot";
    }

    @Test
    public void testRegisterApplicantInBootcamp()
    {
        Applicant applicant = new Applicant(applicantId,
            name,
            birthday,
            degree,
            email,
            new Location(country, city),
            photo,
            telephone,
            career,
            applicantBootcampHistoryIds,
            includedInBootcampIds,
            applicantStageQualifications,
            reports,
            new CurriculumVitae(curriculumVitae, fileNameCV, contentCV), null);
        when(restTemplate.getForEntity(BOOTCAMP_SERVICE_URL + "bootcamp/{bootcampId}",
            Object.class, Map.of("bootcampId", bootcampId))).thenReturn(ResponseEntity.status(
            HttpStatus.OK).body(new Object()));
        when(applicantRepository.findById(applicantId))
            .thenReturn(Optional.of(applicant));
        ApplicantRegisteredInBootcampDTO applicantRegisteredInBootcampDTO =
            registerApplicantUseCase
            .registerApplicantsInBootcamp(List.of(applicantId), bootcampId);
        assertEquals(
            Set.of(String.valueOf(applicantId)),
            applicantRegisteredInBootcampDTO.getRegisteredApplicants());
    }

    @Test
    public void testRegisterApplicantInNonExistentBootcamp()
    {
        when(restTemplate.getForEntity(BOOTCAMP_SERVICE_URL + "bootcamp/{bootcampId}",
            Object.class, Map.of("bootcampId", bootcampId)))
            .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            registerApplicantUseCase
                .registerApplicantsInBootcamp(List.of(applicantId), bootcampId);
        });
        assertSame(ErrorsUtil.ERROR_MESSAGE_ENTITY_NOT_FOUND, exception.getErrorMessage());
    }

    @Test
    public void testRegisterApplicantAtBootcamps()
    {
        Applicant applicant = new Applicant(applicantId,
            name,
            birthday,
            degree,
            email,
            new Location(country, city),
            photo,
            telephone,
            career,
            applicantBootcampHistoryIds,
            includedInBootcampIds,
            applicantStageQualifications,
            reports,
            new CurriculumVitae(curriculumVitae, fileNameCV, contentCV), null);
        when(restTemplate.getForEntity(BOOTCAMP_SERVICE_URL + "bootcamp/{bootcampId}",
            Object.class, Map.of("bootcampId", bootcampId))).thenReturn(ResponseEntity.status(
            HttpStatus.OK).body(new Object()));
        when(applicantRepository.findById(applicantId))
            .thenReturn(Optional.of(applicant));
        ApplicantRegisteredAtBootcampsDTO applicantRegisteredAtBootcampsDTO =
            registerApplicantUseCase
                .registerApplicantInBootcamps(applicantId, List.of( bootcampId));
        assertEquals(
            Set.of(String.valueOf(bootcampId)),
            applicantRegisteredAtBootcampsDTO.getApplicantRegisteredAtBootcamps());
    }

}