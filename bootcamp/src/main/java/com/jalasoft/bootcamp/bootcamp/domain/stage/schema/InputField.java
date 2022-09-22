package com.jalasoft.bootcamp.bootcamp.domain.stage.schema;

import com.jalasoft.bootcamp.becommon.domain.ddd.IsEntity;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

@AllArgsConstructor
@Getter
@IsEntity
@Entity
@Table(name = "field")
@TypeDefs( {
    @TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
    )
})
public class InputField
{
    @EmbeddedId
    private InputFieldId id;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", nullable = false)
    private InputFieldSchema inputFieldSchema;

    @Column(name = "stageId")
    @AttributeOverride(name = "id", column = @Column(name = "stageId", nullable = false))
    private Long stageId;

    /**
     * JPA usage only
     */
    protected InputField()
    {
    }
}
