package com.jalasoft.bootcamp.applicant.domain.applicantstagequalification;

public enum QualificationStatus {
    IN_PROGRESS,
    // DELETED status is for logic operations in domain, this is not stored in the db
    DELETED,
    QUALIFIED_AND_READONLY,
    PASSED,
    FAILED,
    WITHDRAW,
    ON_HOLD,
}
