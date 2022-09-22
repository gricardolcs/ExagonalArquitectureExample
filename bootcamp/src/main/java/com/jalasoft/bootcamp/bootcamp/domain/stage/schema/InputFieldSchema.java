package com.jalasoft.bootcamp.bootcamp.domain.stage.schema;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield.QualificationFieldSchema;

@JsonTypeInfo(
    use = Id.NAME,
    include = As.EXISTING_PROPERTY,
    property = "type",
    visible = true
)
@JsonSubTypes({
    @Type(value = NumericInput.class, name = SchemaFieldType.NUMERIC_INPUT),
    @Type(value = DropdownInput.class, name = SchemaFieldType.DROPDOWN_INPUT),
    @Type(value = UploadFileInput.class, name = SchemaFieldType.UPLOAD_FILE_INPUT)
})
public abstract class InputFieldSchema {

    private final String type;

    @JsonCreator
    protected InputFieldSchema(@JsonProperty("type") String type) {
        this.type = type;
    }

    public abstract QualificationFieldSchema createEmptyQualificationFieldSchema();

    public String getType() {
        return type;
    }
}
