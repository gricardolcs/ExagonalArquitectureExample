package com.jalasoft.bootcamp.bootcamp.domain.bootcamp;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BootcampRepository extends JpaRepository<Bootcamp, Long>
{
    List<Bootcamp> findByName(final String bootcampName);

    boolean existsByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCaseAndIdIsNot(String name, Long bootcampId);

    List<Bootcamp> findAllByBootcampMembersContaining(Long applicantId);

    @Query(value = "SELECT * FROM bootcamp WHERE UPPER(name) LIKE UPPER(CONCAT('%', :criteria,"
        + "'%'))", nativeQuery = true)
    List<Bootcamp> findBootcampByCriteria(String criteria);

    List<Bootcamp> findBootcampsByBootcampMembersNotContains(
        final Long applicantId,
        final Sort sort);

    List<Bootcamp> findByNameIgnoreCaseContainsAndBootcampMembersNotContains(
        final String nameCriteria,
        final Long applicantId,
        final Sort sort);
}
