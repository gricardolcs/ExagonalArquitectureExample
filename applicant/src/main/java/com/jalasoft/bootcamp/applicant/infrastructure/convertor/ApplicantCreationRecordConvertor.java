package com.jalasoft.bootcamp.applicant.infrastructure.convertor;

import com.jalasoft.bootcamp.applicant.domain.applicant.Applicant;
import com.jalasoft.bootcamp.applicant.domain.applicant.ComplementaryAttribute;
import com.jalasoft.bootcamp.applicant.domain.applicant.CurriculumVitae;
import com.jalasoft.bootcamp.becommon.infrastructure.factory.Factory;
import com.jalasoft.bootcamp.becommon.infrastructure.fileparser.Convertor;
import com.jalasoft.bootcamp.becommon.infrastructure.fileparser.Record;
import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.creation.ApplicantCreation;
import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.upload.ApplicantCreationRecord;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.ApplicantCoreAttributes;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.DateParserUtils;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.ValidatorUtils;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
@Qualifier("ApplicantCreationRecordConvertor")
public class ApplicantCreationRecordConvertor implements Convertor<ApplicantCreationRecord>
{

    private static final String ROW_NUMBER_NAME = "N";

    private final Factory<Applicant, ApplicantCreation> applicantCreationFactory;

    private static final Logger logger = LogManager.getLogger(ApplicantCreationRecordConvertor.class);

    @Autowired
    public ApplicantCreationRecordConvertor(
        final Factory<Applicant, ApplicantCreation> applicantCreationFactory)
    {
        this.applicantCreationFactory = applicantCreationFactory;
    }

    @Override
    public List<ApplicantCreationRecord> convert(List<Record> records)
    {
        return records.stream()
            .map(this::getApplicantBootcamp)
            .collect(Collectors.toList());
    }

    private ApplicantCreationRecord getApplicantBootcamp(Record record)
    {
        LocalDate birthday = null;
        String career = "";
        String country = "";
        String city = "";
        String degree = "";
        String email = "";
        String fullName = "";
        String photo = "";
        String phoneNumber = "";
        List<ComplementaryAttribute> complementaryAttributes = new ArrayList<>();
        List<String> skills = new ArrayList<>();
        boolean failed = false;
        int row;

        for (Map.Entry<String, String> entry : record.getValues().entrySet())
        {
            if (ApplicantCoreAttributes.containsCoreAttribute(entry.getKey()) && entry.getValue()
                .isEmpty())
            {
                failed = true;
                logger.error("Failed with some empty applicant atributes");
                break;
            }

            if (ApplicantCoreAttributes.containsCoreAttribute((entry.getKey())))
            {
                if (ApplicantCoreAttributes.BIRTHDAY.isCoreAttribute(entry.getKey()))
                {
                    try
                    {
                        birthday = DateParserUtils.parseStringToLocalDate(entry.getValue());
                    }
                    catch (DateTimeParseException e)
                    {
                        logger.error("Failed with data time value ", e.getMessage());
                        failed = true;
                    }
                    continue;
                }

                if (ApplicantCoreAttributes.EMAIL.isCoreAttribute(entry.getKey()))
                {
                    failed = !ValidatorUtils.isEmailValid(
                        getAttributeValue(entry, ApplicantCoreAttributes.EMAIL, email));
                }

                career = getAttributeValue(entry, ApplicantCoreAttributes.CAREER, career);
                country = getAttributeValue(
                    entry,
                    ApplicantCoreAttributes.CURRENT_COUNTRY,
                    country);
                city = getAttributeValue(entry, ApplicantCoreAttributes.CURRENT_CITY, city);
                degree = getAttributeValue(entry, ApplicantCoreAttributes.DEGREE, degree);
                email = getAttributeValue(entry, ApplicantCoreAttributes.EMAIL, email);
                fullName = getAttributeValue(entry, ApplicantCoreAttributes.FULL_NAME, fullName);
                phoneNumber = getAttributeValue(entry, ApplicantCoreAttributes.PHONE_NUMBER,
                    phoneNumber);
                photo = getAttributeValue(entry, ApplicantCoreAttributes.PHOTO, photo);
            }
            else
            {
                complementaryAttributes
                    .add(new ComplementaryAttribute(entry.getKey(), entry.getValue()));
            }
        }

        row = Integer.parseInt(record.getValues().get(ROW_NUMBER_NAME));
        return new ApplicantCreationRecord(
            getApplicant(
                birthday,
                career,
                country,
                city,
                degree,
                email,
                fullName,
                phoneNumber,
                photo,
                new CurriculumVitae(new byte[0],"", "")),
            complementaryAttributes).setFailed(failed).setRow(row);
    }

    private String getAttributeValue(
        Map.Entry<String, String> entry,
        ApplicantCoreAttributes coreAttribute, String currentValue)
    {
        return coreAttribute.isCoreAttribute(entry.getKey()) ? entry.getValue() : currentValue;
    }

    private Applicant getApplicant(
        final LocalDate birthday, final String career, final String country,
        final String city, final String degree, String email, final String fullName,
        final String phoneNumber, final String photo, final CurriculumVitae curriculumVitae)
    {
        return applicantCreationFactory.create(new ApplicantCreation(fullName,
            birthday, degree, email, country, city, photo, phoneNumber,
            career, Collections.emptyList(), new HashSet<>(),
            Collections.emptyList(), curriculumVitae, Collections.emptyList()));
    }
}
