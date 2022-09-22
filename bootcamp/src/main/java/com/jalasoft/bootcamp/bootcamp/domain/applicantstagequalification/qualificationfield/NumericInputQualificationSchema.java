package com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jalasoft.bootcamp.bootcamp.domain.stage.schema.SchemaFieldType;
import java.util.Objects;
import lombok.Getter;

@Getter
public class NumericInputQualificationSchema extends QualificationFieldSchema<Integer>
{
    private final String label;
    private final Integer qualification;

    public NumericInputQualificationSchema(
        @JsonProperty("label") String label,
        @JsonProperty("qualification") Integer qualification)
    {
        super(SchemaFieldType.NUMERIC_INPUT);
        this.label = label;
        this.qualification = qualification;
    }

    @Override
    public Integer obtainValue()
    {
        return qualification;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof NumericInputQualificationSchema))
        {
            return false;
        }
        NumericInputQualificationSchema that = (NumericInputQualificationSchema) o;
        return Objects.equals(getLabel(), that.getLabel()) && Objects
            .equals(getQualification(), that.getQualification());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getLabel(), getQualification());
    }
}
