package com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification;

import com.jalasoft.bootcamp.becommon.domain.ddd.IsValueObject;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@IsValueObject
@Embeddable
public class ApplicantStageQualificationSubmitDate {

    @Column
    private LocalDateTime submitDate;

    /*
     * Used only by JPA.
     */
    private ApplicantStageQualificationSubmitDate() {
        // JPA usage only.
    }
}
