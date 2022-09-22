package com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.upload;

import com.jalasoft.bootcamp.applicant.domain.applicant.Applicant;
import com.jalasoft.bootcamp.applicant.domain.applicant.ComplementaryAttribute;

import java.util.List;
import java.util.Objects;

public class ApplicantCreationRecord {

    private final Applicant applicant;

    private final List<ComplementaryAttribute> complementaryAttributes;

    private boolean failed;

    private int row;

    public ApplicantCreationRecord(final Applicant applicant
            , List<ComplementaryAttribute> complementaryAttributes) {
        this.applicant = applicant;
        this.complementaryAttributes = complementaryAttributes;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public List<ComplementaryAttribute> getComplementaryAttributes() {
        return complementaryAttributes;
    }

    public boolean isFailed() {
        return failed;
    }

    public int getRow() {
        return row;
    }

    public ApplicantCreationRecord setFailed(boolean failed) {
        this.failed = failed;
        return this;
    }

    public ApplicantCreationRecord setRow(int row) {
        this.row = row;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicantCreationRecord that = (ApplicantCreationRecord) o;
        return failed == that.failed && row == that.row && Objects.equals(applicant, that.applicant)
                && Objects.equals(complementaryAttributes, that.complementaryAttributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicant, complementaryAttributes, failed, row);
    }
}
