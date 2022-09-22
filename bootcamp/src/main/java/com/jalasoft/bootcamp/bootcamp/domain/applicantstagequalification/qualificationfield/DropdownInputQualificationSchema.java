package com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jalasoft.bootcamp.bootcamp.domain.stage.schema.SchemaFieldType;
import java.util.List;
import java.util.Objects;
import lombok.Getter;

@Getter
public class DropdownInputQualificationSchema extends QualificationFieldSchema<String>
{
    private final String label;
    private final List<String> elements;
    private final String selectedElement;

    public DropdownInputQualificationSchema(
        @JsonProperty("label") String label,
        @JsonProperty("elements") List<String> elements,
        @JsonProperty("selectedElement") String selectedElement)
    {
        super(SchemaFieldType.DROPDOWN_INPUT);
        this.label = label;
        this.elements = elements;
        this.selectedElement = selectedElement;
    }

    @Override
    public String obtainValue()
    {
        return selectedElement;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof DropdownInputQualificationSchema))
        {
            return false;
        }
        DropdownInputQualificationSchema that = (DropdownInputQualificationSchema) o;
        return Objects.equals(getLabel(), that.getLabel()) && Objects
            .equals(getElements(), that.getElements()) && Objects
            .equals(getSelectedElement(), that.getSelectedElement());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getLabel(), getElements(), getSelectedElement());
    }
}
