package com.jalasoft.bootcamp.applicant.infrastructure.convertor;

import com.jalasoft.bootcamp.applicant.domain.applicant.Applicant;
import com.jalasoft.bootcamp.applicant.domain.applicant.CurriculumVitae;
import com.jalasoft.bootcamp.applicant.domain.applicant.Location;
import com.jalasoft.bootcamp.applicant.infrastructure.factory.applicant.ApplicantCreationFactory;
import com.jalasoft.bootcamp.becommon.infrastructure.fileparser.Record;
import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.creation.ApplicantCreation;
import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.upload.ApplicantCreationRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicantCreationRecordConvertorTest {

    @Mock
    private ApplicantCreationFactory applicantCreationFactory;

    @InjectMocks
    private ApplicantCreationRecordConvertor convertor;

    private Map<String, String> firstRecordAttributes;

    private Map<String, String> failedRecordAttributes;

    private Applicant applicant;

    private ApplicantCreation applicantCreation;

    @BeforeEach
    public void setUp() {

        this.convertor = new ApplicantCreationRecordConvertor(applicantCreationFactory);
        firstRecordAttributes = new HashMap<>();
        failedRecordAttributes = new HashMap<>();
        loadAttributes();
        applicant = new Applicant(UUID.randomUUID().getLeastSignificantBits(),
            "Luis Rodrigo", LocalDate.of(2000, 5, 4),
            "I've made it! I'm free! (Graduate degree)",
            "rodrigo.info20@gmail.com", new Location("", "Tarija"),
            "https://drive.google.com/open?id=1FBbZCTnhjdvW9OClH-Kt6hzOZdeR2zbo",
            "78233629",
            "Digital Marketing",
            Collections.emptyList(),
            Collections.emptySet(), Collections.emptySet(), Collections.emptyList(),
            new CurriculumVitae(
                new byte[0],
                "filenameCV.pdf",
                "Lorem java dolor mongo amet, .NET spring boot"), null);
        applicantCreation = new ApplicantCreation("Luis Rodrigo",
            LocalDate.of(2000, 5, 4),
            "I've made it! I'm free! (Graduate degree)",
            "rodrigo.info20@gmail.com", "Bolivia", "Tarija",
            "https://drive.google.com/open?id=1FBbZCTnhjdvW9OClH-Kt6hzOZdeR2zbo",
            "78233629","Digital Marketing",
            Collections.emptyList(), Collections.emptySet(), Collections.emptyList(),
            new CurriculumVitae(
                new byte[0],
                "filenameCV.pdf",
                "Lorem java dolor mongo amet, .NET spring boot"),
            Collections.emptyList());
    }

    @Test
    public void testConvertRecordToApplicantCreationRecordWhenRecordHasCoreValuesCorrectly() {
        lenient().when(applicantCreationFactory.create(applicantCreation))
            .thenReturn(applicant);

        Record firstRecord = new Record(firstRecordAttributes);

        List<ApplicantCreationRecord> actual = convertor.convert(List.of(firstRecord));
        ApplicantCreationRecord applicantCreationRecord = actual.get(0);

        assertFalse(applicantCreationRecord.isFailed());
    }

    @Test
    public void testConvertRecordToApplicantCreationRecordBreakWhenCoreAttributeIsEmpty() {
        lenient().when(applicantCreationFactory.create(applicantCreation)).thenReturn(applicant);

        Record failedRecord = new Record(failedRecordAttributes);
        List<ApplicantCreationRecord> actual = convertor.convert(List.of(failedRecord));

        assertTrue(actual.get(0).isFailed());
    }

    @Test
    public void testConvertRecordToApplicantCreationRecordWhenRecordHasEmailCoreValueInvalid_thenReturnApplicantCreationRecordFailed() {
        String emailInvalid = "rodrigo.info20gmail.com";
        int rowNumber = 1;
        firstRecordAttributes.put("Email Address", emailInvalid);

        lenient().when(applicantCreationFactory.create(applicantCreation)).thenReturn(applicant);
        Record firstRecord = new Record(firstRecordAttributes);

        List<ApplicantCreationRecord> actual = convertor.convert(List.of(firstRecord));
        ApplicantCreationRecord applicantCreationRecord = actual.get(0);

        assertTrue(applicantCreationRecord.isFailed());
        assertEquals(rowNumber, applicantCreationRecord.getRow());
    }

    private void loadAttributes() {
        firstRecordAttributes.put("N", "1");
        firstRecordAttributes.put("Email Address", "rodrigo.info20@gmail.com");
        firstRecordAttributes.put("Full name", "Luis Rodrigo");
        firstRecordAttributes.put("Phone number", "78233629");
        firstRecordAttributes.put("Birthday", "4/5/2000");
        firstRecordAttributes.put("Photo",
                "https://drive.google.com/open?id=1FBbZCTnhjdvW9OClH-Kt6hzOZdeR2zbo");
        firstRecordAttributes.put("Current country", "Bolivia");
        firstRecordAttributes.put("Current city", "Tarija");
        firstRecordAttributes.put("What is your career?", "Digital Marketing");
        firstRecordAttributes.put("How far along are you in your career?",
                "I've made it! I'm free! (Graduate degree)");

        failedRecordAttributes.put("N", "2");
        failedRecordAttributes.put("Email Address", "");
        failedRecordAttributes.put("Full name", "Fernando Mercado");
        failedRecordAttributes.put("Phone number", "78437327");
        failedRecordAttributes.put("Birthday", "10/1/1994");
        failedRecordAttributes.put("Photo",
                "https://drive.google.com/open?id=1BT0k67nOzvyhlW1pBVrRaN6fGZAGy2OD");
        firstRecordAttributes.put("Current country", "Bolivia");
        failedRecordAttributes.put("Current city", "Santa Cruz");
        failedRecordAttributes.put("What is your career?", "System Engineering");
        failedRecordAttributes.put("How far along are you in your career?"
                , "I'm still studying and missing just 1 or 2 semesters in my career");
    }
}