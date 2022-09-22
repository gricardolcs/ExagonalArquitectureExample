package com.jalasoft.bootcamp.bootcamp.domain.stage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jalasoft.bootcamp.bootcamp.domain.stage.schema.DropdownInput;
import com.jalasoft.bootcamp.bootcamp.domain.stage.schema.NumericInput;
import com.jalasoft.bootcamp.bootcamp.domain.stage.schema.InputFieldSchema;
import com.jalasoft.bootcamp.bootcamp.domain.stage.schema.UploadFileInput;
import java.util.List;
import org.junit.jupiter.api.Test;

public class InputFieldInputFieldTest {

    @Test
    public void testParseOfJsonToNumericInput() throws JsonProcessingException {
        String json = "{\"type\":\"NUMERIC_INPUT\", \"label\":\"Score(AVG):\"}";
        NumericInput actual = new ObjectMapper().readerFor(InputFieldSchema.class).readValue(json);
        NumericInput expected = new NumericInput("Score(AVG):");
        assertEquals(expected, actual);
    }

    @Test
    public void testParseOfJsonToDropdownInput() throws JsonProcessingException {
        String json = "{\"type\":\"DROPDOWN_INPUT\", \"label\":\"Recommended:\", \"elements\": [\"YES\",\"NO\"]}";
        DropdownInput actual = new ObjectMapper().readerFor(InputFieldSchema.class).readValue(json);
        DropdownInput expected = new DropdownInput("Recommended:", List.of("YES", "NO"), "");
        assertEquals(expected, actual);
    }

    @Test
    public void testParseOfJsonToUploadFileInput() throws JsonProcessingException {
        String json = "{\"type\": \"UPLOAD_FILE_INPUT\", \"label\": \"Upload Report:\"}";
        UploadFileInput actual = new ObjectMapper().readerFor(InputFieldSchema.class).readValue(json);
        UploadFileInput expected = new UploadFileInput("Upload Report:");
        assertEquals(expected, actual);
    }
}