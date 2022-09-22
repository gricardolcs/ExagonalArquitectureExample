package com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicantstagequalification.update;

import static com.jalasoft.bootcamp.applicant.domain.applicantstagequalification.QualificationStatus.FAILED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.jalasoft.bootcamp.applicant.domain.applicant.Applicant;
import com.jalasoft.bootcamp.applicant.domain.applicant.ApplicantRepository;
import com.jalasoft.bootcamp.applicant.domain.applicant.CurriculumVitae;
import com.jalasoft.bootcamp.applicant.domain.applicant.Location;
import com.jalasoft.bootcamp.applicant.domain.applicantstagequalification.ApplicantStageQualification;
import com.jalasoft.bootcamp.applicant.domain.applicantstagequalification.StageName;
import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicantstagequalification.ApplicantStageQualificationDTO;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ApplicantStageQualificationUseCaseTest
{

    @InjectMocks
    private ApplicantStageQualificationUseCase applicantStageQualificationUseCase;

    @Mock
    private ApplicantRepository applicantRepository;

    private Long stageId;
    private Long bootcampId;
    private StageName stageName;
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
    private byte[] curriculumVitae;
    private String fileNameCV;
    private String contentCV;

    @BeforeEach
    public void setUp()
    {
        stageId = -1L;
        bootcampId = -3L;
        stageName = new StageName("Initial");
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
        curriculumVitae = new byte[0];
        fileNameCV = "document.pdf";
        contentCV = "Lorem java dolor mongo amet, .NET spring boot";
    }

    @Test
    public void testAddOrUpdateApplicantStageQualified()
    {
        ApplicantStageQualificationDTO applicantStageQualificationDTO =
            new ApplicantStageQualificationDTO(stageId, stageName.getName(), FAILED, applicantId,
                bootcampId);
        Applicant applicant = new Applicant(applicantId, name, birthday,
            degree, email, new Location(country, city), photo,
            telephone, career,
            Collections.emptyList(), Set.of(bootcampId), new HashSet(), Collections.emptyList(),
            new CurriculumVitae(curriculumVitae, fileNameCV, contentCV), null);
        when(applicantRepository.findById(applicantId)).thenReturn(Optional.of(applicant));
        applicantStageQualificationUseCase
            .addOrUpdateApplicantStageQualified(applicantStageQualificationDTO);
        Set<ApplicantStageQualification> expected = Set.of(new ApplicantStageQualification(
            stageId, stageName, FAILED, applicantId, bootcampId));
        Set<ApplicantStageQualification> actual = applicant.getApplicantStageQualifications();
        assertEquals(expected, actual);
    }

    @Test
    public void testAddOrUpdateApplicantStageQualifiedWithApplicantDoesNotFoundInBootcamp()
    {
        ApplicantStageQualificationDTO applicantStageQualificationDTO =
            new ApplicantStageQualificationDTO(stageId, stageName.getName(), FAILED, applicantId,
                bootcampId);
        Applicant applicant = new Applicant(
            applicantId,
            name,
            birthday,
            degree,
            email,
            new Location(country, city),
            photo,
            telephone,
            career,
            Collections.emptyList(),
            Collections.emptySet(),
            new HashSet(),
            Collections.emptyList(),
            new CurriculumVitae(curriculumVitae, fileNameCV, contentCV), null);
        when(applicantRepository.findById(applicantId)).thenReturn(Optional.of(applicant));
        EntityNotFoundException exception = assertThrows(
            EntityNotFoundException.class,
            () -> applicantStageQualificationUseCase
                .addOrUpdateApplicantStageQualified(applicantStageQualificationDTO));
        assertSame(ErrorsUtil.ERROR_MESSAGE_ENTITY_NOT_FOUND, exception.getErrorMessage());
    }

    @Test
    public void testAddOrUpdateApplicantStageQualifiedWithNonexistentApplicantId()
    {
        ApplicantStageQualificationDTO applicantStageQualificationDTO =
            new ApplicantStageQualificationDTO(stageId, stageName.getName(),
                FAILED, applicantId, bootcampId);
        when(applicantRepository.findById(applicantId)).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(
            EntityNotFoundException.class,
            () -> applicantStageQualificationUseCase
                .addOrUpdateApplicantStageQualified(applicantStageQualificationDTO));
        assertSame(ErrorsUtil.ERROR_MESSAGE_ENTITY_NOT_FOUND, exception.getErrorMessage());
    }
}
