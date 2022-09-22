package com.jalasoft.bootcamp.applicant.domain.applicantbootcamphistory;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicantBootcampHistoryRepository extends
    MongoRepository<ApplicantBootcampHistory, Long>
{
    Optional<ApplicantBootcampHistory> findByBootcampIdAndApplicantId(
        Long bootcampId,
        Long applicantId);

    List<ApplicantBootcampHistory> findAllByApplicantId(Long applicantId);
}
