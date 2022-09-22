package com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.creation;

import com.jalasoft.bootcamp.applicant.domain.applicant.CurriculumVitae;
import com.jalasoft.bootcamp.applicant.domain.applicant.Report;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public final class ApplicantCreation
{
    private final String fullName;
    private final LocalDate birthday;
    private final String degree;
    private final String email;
    private final String country;
    private final String city;
    private final String photo;
    private final String telephone;
    private final String career;

    private final List<Long> applicantBootcampHistoryIdList;
    private final Set<Long> includedInBootcampIds;

    private final List<Report> reports;
    private final CurriculumVitae curriculumVitae;
    private final List<String> skills;
}
