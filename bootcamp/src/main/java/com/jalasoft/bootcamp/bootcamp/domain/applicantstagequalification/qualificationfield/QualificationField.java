package com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield;

import com.jalasoft.bootcamp.becommon.domain.ddd.IsEntity;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "qualificationField")
@TypeDefs( {
    @TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
    )
})
public class QualificationField
{
    @Id
    private Long id;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", nullable = false)
    private QualificationFieldSchema qualificationFieldSchema;

    @Column(name = "applicantStageQualificationId")
    @AttributeOverride(name = "id", column = @Column(name = "applicantStageQualificationId",
        nullable = false))
    private Long applicantStageQualificationId;

    /**
     * JPA usage only
     */
    protected QualificationField()
    {
    }

    public void changeQualificationField(QualificationFieldSchema qualificationFieldSchema)
    {
        this.qualificationFieldSchema = qualificationFieldSchema;
    }
}
