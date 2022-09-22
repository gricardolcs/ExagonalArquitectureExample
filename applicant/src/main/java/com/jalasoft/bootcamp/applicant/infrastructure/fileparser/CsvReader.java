package com.jalasoft.bootcamp.applicant.infrastructure.fileparser;

import com.jalasoft.bootcamp.becommon.domain.exceptions.InvalidFileException;
import com.jalasoft.bootcamp.becommon.infrastructure.fileparser.Record;
import com.jalasoft.bootcamp.becommon.infrastructure.fileparser.FileReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Qualifier("CsvReader")
public class CsvReader implements FileReader
{
    @Override
    public List<Record> read(final InputStreamReader file)
    {
        try
        {
            CSVParser csvParser = new CSVParser(file, CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withIgnoreHeaderCase()
                .withTrim());

            List<String> headers = csvParser.getHeaderNames();

            return csvParser.getRecords().stream().map(csvRecord -> {
                Map<String, String> values = new HashMap<>();

                IntStream.range(0, csvRecord.size()).forEach(index -> {
                    values.put(headers.get(index), csvRecord.get(index));
                });

                return new Record(values);
            }).collect(Collectors.toList());
        }
        catch (IllegalArgumentException | IOException e)
        {
            throw new InvalidFileException("file content", e.getMessage());
        }
    }
}
