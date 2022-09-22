package com.jalasoft.bootcamp.bootcamp.domain.stage.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield.NumericInputQualificationSchema;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield.QualificationFieldSchema;
import java.util.Objects;
import lombok.Getter;

@Getter
public class NumericInput extends InputFieldSchema
{
    private final String label;

    public NumericInput(@JsonProperty("label") String label)
    {
        super(SchemaFieldType.NUMERIC_INPUT);
        this.label = label;
    }

    @Override
    public QualificationFieldSchema createEmptyQualificationFieldSchema()
    {
        return new NumericInputQualificationSchema(label, null);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof NumericInput))
        {
            return false;
        }
        NumericInput that = (NumericInput) o;
        return getLabel().equals(that.getLabel());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getLabel());
    }
}
