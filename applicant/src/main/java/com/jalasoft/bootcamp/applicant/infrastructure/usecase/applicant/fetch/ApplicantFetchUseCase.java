package com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.fetch;

import static com.jalasoft.bootcamp.applicant.domain.applicantstagequalification.QualificationStatus.FAILED;
import static com.jalasoft.bootcamp.applicant.domain.applicantstagequalification.QualificationStatus.IN_PROGRESS;

import com.jalasoft.bootcamp.applicant.domain.applicant.Applicant;
import com.jalasoft.bootcamp.applicant.domain.applicant.ApplicantRepository;
import com.jalasoft.bootcamp.applicant.domain.applicantstagequalification.ApplicantStageQualification;
import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.filter.ApplicantFilterUseCase;
import com.jalasoft.bootcamp.applicant.infrastructure.utils.Filter;
import com.jalasoft.bootcamp.becommon.domain.ddd.IsUseCase;
import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantDTO;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@IsUseCase
@Service
public class ApplicantFetchUseCase
{
    private static final boolean FEATURE_FLAG_IS_GOOGLE_DRIVE_FUNCTIONALITY_IMPLEMENTED = false;

    private final ApplicantRepository applicantRepository;

    private final ApplicantFilterUseCase applicantFilterUseCase;


    @Autowired
    public ApplicantFetchUseCase(
        ApplicantRepository applicantRepository,
        final ApplicantFilterUseCase applicantFilterUseCase)
    {
        this.applicantRepository = applicantRepository;
        this.applicantFilterUseCase = applicantFilterUseCase;
    }

    public Applicant findById(long id)
    {
        Applicant applicant = applicantRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                Field.ID,
                ErrorsUtil.getDescription(
                    ErrorsUtil.ERROR_DESCRIPTION_APPLICANT_NOT_FOUND,
                    Field.ID, String.valueOf(id))
            ));
        /*
         * TODO(ronal.choque): This part will be changed when we already have
         *  the integration with googleDrive and the respective implementations.
         */

        if (!FEATURE_FLAG_IS_GOOGLE_DRIVE_FUNCTIONALITY_IMPLEMENTED)
        {
            applicant.setPhoto("");
        }
        return applicant;
    }

    public List<ApplicantDTO> fetchAll()
    {
        return applicantRepository.findAllByOrderByFullNameAsc().stream()
            .map(ApplicantDTO::new).collect(Collectors.toList());
    }

    public List<ApplicantDTO> getAllApplicantsExcludingBootcampApplicants(
        final Long bootcampId, final String criteria, final Sort sort)
    {
        List<Applicant> applicants =
            applicantRepository.findApplicantsByIncludedInBootcampIdsIsNot(bootcampId, sort);

        if (criteria.isBlank())
        {
            return applicantRepository.findApplicantsByIncludedInBootcampIdsIsNot(bootcampId, sort)
                .stream().map(applicant -> new ApplicantDTO(
                        applicant.getId(),
                        applicant.getFullName(),
                        applicant.getLocation().getCountry(),
                        applicant.getSkills()
                    )
                ).collect(Collectors.toList());
        }
        else
        {
            return applicantRepository.findApplicantsByCriteriaAndIncludedInBootcampIdsIsNot(
                criteria,
                bootcampId,
                sort).stream().map(ApplicantDTO::new).collect(Collectors.toList());
        }
    }

    public Page<Applicant> fetchAllApplicantsIncludedInBootcamp(
        long bootcampId, final boolean unpaged, final Pageable pageable)
    {
        Page<Applicant> applicantsPage;
        if (unpaged)
        {
            List<Applicant> bootcampApplicants = applicantRepository
                .findApplicantsByIncludedInBootcampIds(bootcampId, pageable.getSort());
            applicantsPage = new PageImpl<>(bootcampApplicants, Pageable.unpaged(),
                bootcampApplicants.size());
        }
        else
        {
            applicantsPage = applicantRepository
                .findApplicantsByIncludedInBootcampIds(bootcampId, pageable);
        }
        applicantsPage.map(applicant -> {
            Set<ApplicantStageQualification> collect = applicant.getApplicantStageQualifications()
                .stream()
                .filter(applicantStageQualification -> applicantStageQualification.getBootcampId()
                    == bootcampId).collect(Collectors.toSet());
            applicant.setApplicantStageQualifications(collect);
            return applicant;
        });
        return applicantsPage;
    }

    private boolean hasAnInProgressOrDisqualifiedStatus(
        long[] stageIds, final ApplicantStageQualification applicantStageQualification)
    {
        return Arrays.stream(stageIds)
            .anyMatch(id -> applicantStageQualification.getStageId() == id
                && (applicantStageQualification.getQualificationStatus()
                .equals(IN_PROGRESS)
                || applicantStageQualification.getQualificationStatus()
                .equals(FAILED)));
    }

    public List<ApplicantDTO> findByEmail(final String value)
    {
        return toDTO(applicantRepository.findApplicantsByEmail(value));
    }

    public List<ApplicantDTO> findByLocationCountry(final String country)
    {
        return toDTO(applicantRepository.findApplicantsByLocation_Country(country));
    }

    @Cacheable(cacheNames = "applicants", key="")
    public List<ApplicantDTO> findByFilters(
        final Long bootcampId, final List<Filter> filters)
    {
        List<Applicant> applicants = new ArrayList<>();
        for(Filter filter:filters){
                sanitizeFilter(filter);
                applicants.addAll(applicantFilterUseCase.getApplicantsByFiltering(bootcampId,
                    filter));
                if (filter.getAttribute().equals(Field.STAGE))
                {
                    long[] stageIds = filter.getValues().stream().map(stageId->
                            Long.parseLong(stageId)).mapToLong(Long::longValue)
                        .toArray();
                    applicants.stream().filter(applicant -> applicant.getApplicantStageQualifications().stream()
                        .anyMatch(
                            applicantStageQualification -> hasAnInProgressOrDisqualifiedStatus(
                                stageIds,
                                applicantStageQualification))).collect(Collectors.toList());
                }
        }
        return toDTO(applicants);
    }

    public void sanitizeFilter(final Filter filter) {
        filter.getValues().forEach(x -> x.replaceAll("[^a-zA-Z0-9]", ""));
    }

    private List<ApplicantDTO> toDTO(final List<Applicant> applicants)
    {
        return applicants.stream().map(ApplicantDTO::new).collect(Collectors.toList());
    }
}
