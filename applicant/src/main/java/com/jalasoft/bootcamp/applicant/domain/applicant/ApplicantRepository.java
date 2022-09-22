package com.jalasoft.bootcamp.applicant.domain.applicant;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantRepository extends MongoRepository<Applicant, Long>
{
    boolean existsByEmail(final String email);

    Optional<Applicant> findByEmail(final String email);

    List<Applicant> findApplicantsByEmail(final String email);

    List<Applicant> findApplicantsByLocation_Country(final String country);

    List<Applicant> findApplicantsByIncludedInBootcampIds(
        Long bootcampId,
        final Sort sort);

    Page<Applicant> findApplicantsByIncludedInBootcampIds(
        Long bootcampId,
        final Pageable pageable);

    List<Applicant> findAllByOrderByFullNameAsc();

    List<Applicant> findApplicantsByIncludedInBootcampIdsIsNot(
        final Long bootcampId,
        final Sort sort);

    @Query("{$and:[{'fullName':{$regex:/?0/,$options:'i'}},{'includedInBootcampIds':{$ne:?1}}]}")
    List<Applicant> findApplicantsByCriteriaAndIncludedInBootcampIdsIsNot(
        final String criteria,
        final Long bootcampId, final Sort sort);
}
