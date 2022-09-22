package com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jalasoft.bootcamp.bootcamp.domain.stage.schema.SchemaFieldType;
import java.util.Objects;
import lombok.Getter;

@Getter
/**
 * TODO (cristal.flores): This qualification type didn't complete yet
 * because a reference to the uploaded file may be added.
 */
public class UploadFileQualificationSchema extends QualificationFieldSchema
{
    private final String label;
    private final String fileName;

    public UploadFileQualificationSchema(
        @JsonProperty("label") String label,
        @JsonProperty("fileName") String fileName)
    {
        super(SchemaFieldType.UPLOAD_FILE_INPUT);
        this.label = label;
        this.fileName = fileName;
    }

    @Override
    public String obtainValue()
    {
        return fileName;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof UploadFileQualificationSchema))
        {
            return false;
        }
        UploadFileQualificationSchema that = (UploadFileQualificationSchema) o;
        return Objects.equals(getLabel(), that.getLabel()) && Objects
            .equals(getFileName(), that.getFileName());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getLabel(), getFileName());
    }
}
