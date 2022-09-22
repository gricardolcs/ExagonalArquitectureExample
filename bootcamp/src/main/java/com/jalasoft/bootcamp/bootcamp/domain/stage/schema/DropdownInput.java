package com.jalasoft.bootcamp.bootcamp.domain.stage.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield.DropdownInputQualificationSchema;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield.QualificationFieldSchema;
import java.util.List;
import java.util.Objects;
import lombok.Getter;

@Getter
public class DropdownInput extends InputFieldSchema
{
    private final String label;
    private final List<String> elements;
    private final String selectedElement;

    public DropdownInput(
        @JsonProperty("label") String label,
        @JsonProperty("elements") List<String> elements,
        @JsonProperty("selectedElement") String selectedElement)
    {
        super(SchemaFieldType.DROPDOWN_INPUT);
        this.label = label;
        this.elements = elements;
        this.selectedElement = Objects.toString(selectedElement, "");
    }

    @Override
    public QualificationFieldSchema createEmptyQualificationFieldSchema()
    {
        return new DropdownInputQualificationSchema(label, elements, selectedElement);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof DropdownInput))
        {
            return false;
        }
        DropdownInput that = (DropdownInput) o;
        return getLabel().equals(that.getLabel()) && getElements().equals(that.getElements())
            && getSelectedElement().equals(that.selectedElement);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getLabel(), getElements(), getSelectedElement());
    }
}
