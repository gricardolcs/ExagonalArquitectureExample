package com.jalasoft.bootcamp.applicant.domain.applicantbootcamphistory;

import com.jalasoft.bootcamp.applicant.domain.applicant.ComplementaryAttribute;
import com.jalasoft.bootcamp.becommon.domain.ddd.IsAggregateRoot;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.List;

@Getter
@IsAggregateRoot
public class ApplicantBootcampHistory extends AbstractAggregateRoot<ApplicantBootcampHistory>
{
    @Id
    private final Long applicantBootcampHistoryId;

    private final Long bootcampId;

    private final Long applicantId;

    private List<ComplementaryAttribute> complementaryAttributes;

    @PersistenceConstructor
    public ApplicantBootcampHistory(
        Long applicantBootcampHistoryId
        , final Long bootcampId, final Long applicantId
        , List<ComplementaryAttribute> complementaryAttributes)
    {
        this.applicantBootcampHistoryId = applicantBootcampHistoryId;
        this.bootcampId = bootcampId;
        this.applicantId = applicantId;
        this.complementaryAttributes = complementaryAttributes;
    }

    public void updateComplementaryAttributes(List<ComplementaryAttribute> complementaryAttributes)
    {
        this.complementaryAttributes = complementaryAttributes;
    }
}
