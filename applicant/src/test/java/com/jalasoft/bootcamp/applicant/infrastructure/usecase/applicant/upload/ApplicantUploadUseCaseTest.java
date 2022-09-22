package com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.upload;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jalasoft.bootcamp.applicant.domain.applicant.Applicant;
import com.jalasoft.bootcamp.applicant.domain.applicant.ApplicantRepository;
import com.jalasoft.bootcamp.applicant.domain.applicant.ComplementaryAttribute;
import com.jalasoft.bootcamp.applicant.domain.applicant.CurriculumVitae;
import com.jalasoft.bootcamp.applicant.domain.applicant.Location;
import com.jalasoft.bootcamp.applicant.domain.applicantbootcamphistory.ApplicantBootcampHistory;
import com.jalasoft.bootcamp.applicant.domain.applicantbootcamphistory.ApplicantBootcampHistoryRepository;
import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantUploadDTO;
import com.jalasoft.bootcamp.applicant.infrastructure.eventpublisher.EventPublisher;
import com.jalasoft.bootcamp.becommon.infrastructure.factory.Factory;
import com.jalasoft.bootcamp.becommon.infrastructure.fileparser.FileParser;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class ApplicantUploadUseCaseTest
{

    private static final long BOOTCAMP_ID = -1029384756;
    @Mock
    private FileParser<ApplicantCreationRecord> fileParser;
    @Mock
    private ApplicantRepository applicantRepository;
    @Mock
    private ApplicantBootcampHistoryRepository applicantBootcampHistoryRepository;
    @Mock
    private EventPublisher eventPublisher;
    @Mock
    private Factory<ApplicantBootcampHistory, ApplicantBootcampHistoryCreation> applicantBootcampHistoryFactory;
    @InjectMocks
    private ApplicantUploadUseCase applicantUploadUseCase;
    private Applicant applicant;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        long applicantId = UUID.randomUUID().getLeastSignificantBits();
        applicant = new Applicant(applicantId, "Carlos Perez",
            LocalDate.of(2000, 10, 23),
            "Bachelor", "carlos.perez@gmail.com",
            new Location("Bolivia", "Cochabamba"), "photo-url.com",
            "59167123340", "System Engineering",
            Collections.emptyList(), new HashSet(), Collections.emptySet(), Collections.emptyList(),
            new CurriculumVitae(
                new byte[0],
                "filenameCV.pdf",
                "Lorem java dolor mongo amet, .NET spring boot"), null);
    }

    @Test
    void testUploadNewApplicantSuccessfully()
    {
        ComplementaryAttribute agile = new ComplementaryAttribute(
            "Has experience in agile?",
            "Yes");
        ComplementaryAttribute autodidact = new ComplementaryAttribute("Autodidact?", "Yes");
        List<ComplementaryAttribute> complementaryAttributes = List.of(agile, autodidact);

        ApplicantCreationRecord applicantCreationRecord = new ApplicantCreationRecord(
            applicant,
            complementaryAttributes).setFailed(false).setRow(1);

        List<ApplicantCreationRecord> applicantCreationRecordList = new ArrayList<>();
        applicantCreationRecordList.add(applicantCreationRecord);

        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        Mockito.when(fileParser.parse(inputStreamReader))
            .thenReturn(applicantCreationRecordList);

        Mockito.when(applicantRepository.existsByEmail("carlos.perez@gmail.com"))
            .thenReturn(false);

        ApplicantBootcampHistory applicantBootcampHistory = new ApplicantBootcampHistory(
            UUID.randomUUID().getLeastSignificantBits(), BOOTCAMP_ID, applicant.getId(),
            applicantCreationRecord.getComplementaryAttributes());

        Mockito.when(applicantBootcampHistoryFactory.create(new ApplicantBootcampHistoryCreation(
                BOOTCAMP_ID, applicant.getId(), complementaryAttributes)))
            .thenReturn(applicantBootcampHistory);

        Mockito.when(applicantBootcampHistoryRepository
                .findByBootcampIdAndApplicantId(BOOTCAMP_ID, applicant.getId()))
            .thenReturn(Optional.of(applicantBootcampHistory));

        Mockito.when(applicantBootcampHistoryFactory.create(
                new ApplicantBootcampHistoryCreation(BOOTCAMP_ID, applicant.getId(),
                    complementaryAttributes)))
            .thenReturn(applicantBootcampHistory);

        Mockito.when(applicantBootcampHistoryRepository.findAllByApplicantId(applicant.getId()))
            .thenReturn(List.of(applicantBootcampHistory));

        ApplicantUploadDTO response = applicantUploadUseCase
            .uploadApplicants(inputStreamReader, BOOTCAMP_ID);

        assertEquals("", response.getFailedMessage());
        assertEquals(Collections.emptyList(), response.getFailedRows());
        assertEquals(
            List.of(String.valueOf(applicant.getId())),
            response.getApplicantsIds());
    }

    @Test
    void testUploadNewApplicantWithWrongEmailAddress_thenReturnInformationError()
    {
        int rowNumber = 1;
        List<ComplementaryAttribute> complementaryAttributes = Collections.emptyList();

        ApplicantCreationRecord applicantCreationRecord = new ApplicantCreationRecord(
            applicant,
            complementaryAttributes).setFailed(true).setRow(rowNumber);

        List<ApplicantCreationRecord> applicantCreationRecordList = new ArrayList<>();
        applicantCreationRecordList.add(applicantCreationRecord);

        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        Mockito.when(fileParser.parse(inputStreamReader))
            .thenReturn(applicantCreationRecordList);

        Mockito.when(applicantRepository.existsByEmail("carlos.perez[at]gmail.com"))
            .thenReturn(false);

        ApplicantUploadDTO response = applicantUploadUseCase
            .uploadApplicants(inputStreamReader, BOOTCAMP_ID);

        assertEquals(ApplicantUploadUseCase.UPLOADED_ERROR_MESSAGE, response.getFailedMessage());
        assertEquals(List.of(rowNumber), response.getFailedRows());
        assertEquals(Collections.emptyList(), response.getApplicantsIds());
    }
}