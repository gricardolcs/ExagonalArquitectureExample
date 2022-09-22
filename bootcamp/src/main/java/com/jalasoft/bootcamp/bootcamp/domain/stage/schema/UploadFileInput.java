package com.jalasoft.bootcamp.bootcamp.domain.stage.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield.QualificationFieldSchema;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield.UploadFileQualificationSchema;
import java.util.Objects;
import lombok.Getter;

@Getter
public class UploadFileInput extends InputFieldSchema
{
    private final String label;

    public UploadFileInput(@JsonProperty("label") String label)
    {
        super(SchemaFieldType.UPLOAD_FILE_INPUT);
        this.label = label;
    }

    @Override
    public QualificationFieldSchema createEmptyQualificationFieldSchema()
    {
        return new UploadFileQualificationSchema(label, "");
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof UploadFileInput))
        {
            return false;
        }
        UploadFileInput that = (UploadFileInput) o;
        return getLabel().equals(that.getLabel());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getLabel());
    }
}
