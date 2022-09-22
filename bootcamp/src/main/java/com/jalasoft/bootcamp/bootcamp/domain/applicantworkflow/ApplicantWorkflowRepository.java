package com.jalasoft.bootcamp.bootcamp.domain.applicantworkflow;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantWorkflowRepository extends JpaRepository<ApplicantWorkflow, Long>
{
    boolean existsByApplicantIdAndBootcampId(final Long applicantId, final Long bootcampId);

    Optional<ApplicantWorkflow> findByApplicantIdAndBootcampId(final Long applicantId,
        final Long bootcampId);

    List<ApplicantWorkflow> findAllByApplicantId(final Long applicantId);

    @Modifying
    @Query(value = "DELETE FROM qualification_field WHERE applicant_stage_qualification_id = "
        + ":applicantStageQualificationId", nativeQuery = true)
    void deleteQualificationFieldsByApplicantStageQualificationId(
        @Param("applicantStageQualificationId") final Long applicantStageQualificationId);

    @Modifying
    @Query(value = "DELETE FROM applicant_stage_qualification WHERE id = "
        + ":applicantStageQualificationId", nativeQuery = true)
    void deleteApplicantStageQualificationById(
        @Param("applicantStageQualificationId") final Long applicantStageQualificationId);
}
