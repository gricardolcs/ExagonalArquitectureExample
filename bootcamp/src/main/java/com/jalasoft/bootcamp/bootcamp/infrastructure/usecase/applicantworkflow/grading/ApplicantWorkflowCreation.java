package com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.applicantworkflow.grading;

import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.Bootcamp;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApplicantWorkflowCreation {

    private final long applicantId;

    private final Bootcamp bootcamp;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApplicantWorkflowCreation)) {
            return false;
        }
        ApplicantWorkflowCreation that = (ApplicantWorkflowCreation) o;
        return getApplicantId() == that.getApplicantId() && Objects
            .equals(getBootcamp(), that.getBootcamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getApplicantId(), getBootcamp());
    }
}
