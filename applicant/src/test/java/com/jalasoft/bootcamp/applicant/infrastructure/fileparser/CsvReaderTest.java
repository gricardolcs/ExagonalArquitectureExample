package com.jalasoft.bootcamp.applicant.infrastructure.fileparser;

import com.jalasoft.bootcamp.becommon.domain.exceptions.InvalidFileException;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.ApplicantCoreAttributes;
import com.jalasoft.bootcamp.becommon.infrastructure.fileparser.FileReader;
import com.jalasoft.bootcamp.becommon.infrastructure.fileparser.Record;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CsvReaderTest
{

    private FileReader fileReader;

    @BeforeEach
    public void setUp()
    {
        this.fileReader = new CsvReader();
    }

    @Test
    public void testGetRecordCollectionSuccessfullyWhenTheCsvFileHasACorrectFormat()
    throws FileNotFoundException
    {
        File file = new File("src/test/resources/csv/three_applicants.csv");
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

        List<Record> actual = fileReader.read(inputStreamReader);

        Record actualFirstRecord = actual.get(0);
        Record actualSecondRecord = actual.get(1);
        Record actualThirdRecord = actual.get(2);

        assertEquals(3, actual.size());
        assertEquals("Luis Rodrigo", actualFirstRecord.getValues().get("Full name"));
        assertEquals("Fernando Mercado", actualSecondRecord.getValues().get("Full name"));
        assertEquals("Israel Jesus Perez Chavez", actualThirdRecord.getValues().get("Full name"));

        assertEquals(
            "rodrigo.info20@gmail.com",
            actualFirstRecord.getValues().get("Email Address"));
        assertEquals(
            "fernandomercado529@gmail.com",
            actualSecondRecord.getValues().get("Email Address"));
        assertEquals("pcisrra@hotmail.com", actualThirdRecord.getValues().get("Email Address"));
    }

    @Test
    public void testCoreHeadersArePresentWhenTheCsvFileHasACorrectFormat()
    throws FileNotFoundException
    {
        File file = new File("src/test/resources/csv/three_applicants.csv");
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

        List<Record> actualRecords = fileReader.read(inputStreamReader);

        ApplicantCoreAttributes[] headers = ApplicantCoreAttributes.values();
        for (Record current : actualRecords)
        {
            for (ApplicantCoreAttributes header : headers)
            {
                String headerValue = header.getValue().iterator().next();
                assertTrue(current.getValues().containsKey(headerValue));
            }
        }
    }

    @Test
    public void testThrownExceptionWhenTheCsvFileHasInvalidArgument() throws FileNotFoundException
    {
        File file = new File("src/test/resources/csv/three_applicants_with_errors.csv");
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        Assertions.assertThrows(InvalidFileException.class, () -> {
            fileReader.read(inputStreamReader);
        });
    }
}