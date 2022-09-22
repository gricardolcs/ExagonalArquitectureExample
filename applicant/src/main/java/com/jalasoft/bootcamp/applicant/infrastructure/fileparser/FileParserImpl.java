package com.jalasoft.bootcamp.applicant.infrastructure.fileparser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.util.List;
import com.jalasoft.bootcamp.becommon.infrastructure.fileparser.FileParser;
import com.jalasoft.bootcamp.becommon.infrastructure.fileparser.FileReader;
import com.jalasoft.bootcamp.becommon.infrastructure.fileparser.Record;
import com.jalasoft.bootcamp.becommon.infrastructure.fileparser.Convertor;

/**
 * @param <T> Indicates the Type in which the
 *            {@link Record Record} type will be converted,
 *            Typically it will be an Entity class
 */
@Component
public class FileParserImpl<T> implements FileParser<T> {

    private final FileReader fileReader;
    private final Convertor<T> convertor;

    @Autowired
    public FileParserImpl(final FileReader fileReader, final Convertor<T> convertor) {
        this.fileReader = fileReader;
        this.convertor = convertor;
    }

    @Override
    public List<T> parse(InputStreamReader file) {
        List<Record> records = fileReader.read(file);
        return convertor.convert(records);
    }
}
