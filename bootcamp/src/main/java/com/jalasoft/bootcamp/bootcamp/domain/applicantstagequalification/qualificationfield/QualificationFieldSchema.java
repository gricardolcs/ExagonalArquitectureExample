package com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.jalasoft.bootcamp.bootcamp.domain.stage.schema.SchemaFieldType;

@JsonTypeInfo(
    use = Id.NAME,
    include = As.EXISTING_PROPERTY,
    property = "type",
    visible = true
)
@JsonSubTypes({
    @Type(value = NumericInputQualificationSchema.class, name = SchemaFieldType.NUMERIC_INPUT),
    @Type(value = DropdownInputQualificationSchema.class, name = SchemaFieldType.DROPDOWN_INPUT),
    @Type(value = UploadFileQualificationSchema.class, name = SchemaFieldType.UPLOAD_FILE_INPUT)
})
public abstract class QualificationFieldSchema<T> {

    private final String type;

    @JsonCreator
    protected QualificationFieldSchema(@JsonProperty("type") String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public abstract String getLabel();

    public abstract T obtainValue();
}
