package com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification;

import com.jalasoft.bootcamp.becommon.domain.ddd.IsValueObject;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@IsValueObject
@Embeddable
public class ApplicantStageQualificationComment implements Serializable {

    @Column
    @Size(max = 1000)
    private String comment;

    /*
     * JPA usage only
     */
    protected ApplicantStageQualificationComment() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApplicantStageQualificationComment that = (ApplicantStageQualificationComment) o;
        return Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comment);
    }
}
