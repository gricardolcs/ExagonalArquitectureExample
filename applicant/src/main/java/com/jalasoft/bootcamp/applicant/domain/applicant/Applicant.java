package com.jalasoft.bootcamp.applicant.domain.applicant;

import com.jalasoft.bootcamp.applicant.domain.applicantstagequalification.ApplicantStageQualification;
import com.jalasoft.bootcamp.becommon.domain.ddd.IsAggregateRoot;
import com.jalasoft.bootcamp.becommon.domain.ddd.IsEntity;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

@Getter
@IsEntity
@IsAggregateRoot
public class Applicant extends AbstractAggregateRoot<Applicant>
{
    @Id
    private final Long id;
    private final String email;
    private final Set<Long> includedInBootcampIds;
    private Set<ApplicantStageQualification> applicantStageQualifications;
    private LocalDate birthday;
    private String degree;
    private String fullName;
    private Location location;
    private String photo;
    private String telephone;
    private String career;
    private List<Long> applicantBootcampHistoryIds;
    private List<Report> reports;
    private CurriculumVitae curriculumVitae;
    private List<String> skills;

    @PersistenceConstructor
    public Applicant(
        Long id, final String fullName, LocalDate birthday, String degree, String email,
        Location location, String photo, String telephone, String career,
        List<Long> applicantBootcampHistoryIds, Set<Long> includedInBootcampIds,
        Set<ApplicantStageQualification> applicantStageQualifications, List<Report> reports,
        CurriculumVitae curriculumVitae, List<String> skills)
    {
        this.id = id;
        this.birthday = birthday;
        this.degree = degree;
        this.email = email;
        this.fullName = fullName;
        this.location = location;
        this.photo = photo;
        this.telephone = telephone;
        this.career = career;
        this.applicantBootcampHistoryIds = applicantBootcampHistoryIds;
        this.includedInBootcampIds = includedInBootcampIds;
        this.applicantStageQualifications = applicantStageQualifications;
        this.reports = reports;
        this.curriculumVitae = curriculumVitae;
        this.skills = skills;
    }

    public void setBirthday(LocalDate birthday)
    {
        this.birthday = birthday;
    }

    public void setDegree(String degree)
    {
        this.degree = degree;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public void setLocation(Location location)
    {
        this.location = location;
    }

    public void setPhoto(String photo)
    {
        this.photo = photo;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    public void setCareer(String career)
    {
        this.career = career;
    }

    public void addApplicantBootcampHistoriesIds(
        List<Long> applicantBootcampHistoryIds)
    {
        this.applicantBootcampHistoryIds = applicantBootcampHistoryIds;
    }

    public void addBootcampId(Long bootcampId)
    {
        if (!includedInBootcampIds.contains(bootcampId))
        {
            this.includedInBootcampIds.add(bootcampId);
        }
    }

    public void addApplicantStageQualifiedId(
        ApplicantStageQualification applicantStageQualification)
    {
        applicantStageQualifications.add(applicantStageQualification);
    }

    public void setApplicantStageQualifications(
        Set<ApplicantStageQualification> applicantStageQualifications)
    {
        this.applicantStageQualifications = applicantStageQualifications;
    }

    public void setReports(List<Report> reports)
    {
        this.reports = reports;
    }

    public void setCurriculumVitae(CurriculumVitae curriculumVitae)
    {
        this.curriculumVitae = curriculumVitae;
    }

    public void setSkills(final List<String> skills)
    {
        this.skills = skills;
    }
}
