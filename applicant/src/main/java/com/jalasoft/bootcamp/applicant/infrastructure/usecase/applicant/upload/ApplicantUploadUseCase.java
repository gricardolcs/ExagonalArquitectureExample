package com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.upload;

import com.jalasoft.bootcamp.applicant.domain.applicant.Applicant;
import com.jalasoft.bootcamp.applicant.domain.applicant.ApplicantRepository;
import com.jalasoft.bootcamp.applicant.domain.applicant.ComplementaryAttribute;
import com.jalasoft.bootcamp.applicant.domain.applicantbootcamphistory.ApplicantBootcampHistory;
import com.jalasoft.bootcamp.applicant.domain.applicantbootcamphistory.ApplicantBootcampHistoryRepository;
import com.jalasoft.bootcamp.becommon.domain.ddd.IsUseCase;
import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantUploadDTO;
import com.jalasoft.bootcamp.applicant.infrastructure.eventpublisher.EventPublisher;
import com.jalasoft.bootcamp.applicant.infrastructure.events.BootcampApplicantsUploadedEvent;
import com.jalasoft.bootcamp.becommon.infrastructure.factory.Factory;
import com.jalasoft.bootcamp.becommon.infrastructure.fileparser.FileParser;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.Field;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@IsUseCase
@Service
public class ApplicantUploadUseCase
{
    public static final String UPLOADED_ERROR_MESSAGE =
        "There are applicants that couldn't be loaded, please review them";
    private final ApplicantRepository applicantRepository;
    private final ApplicantBootcampHistoryRepository applicantBootcampHistoryRepository;
    private final FileParser<ApplicantCreationRecord> fileParser;
    private final EventPublisher eventPublisher;
    private final Factory<ApplicantBootcampHistory, ApplicantBootcampHistoryCreation> applicantBootcampHistoryFactory;

    @Autowired
    public ApplicantUploadUseCase(
        final FileParser<ApplicantCreationRecord> fileParser,
        final ApplicantRepository applicantRepository,
        final ApplicantBootcampHistoryRepository applicantBootcampHistoryRepository,
        final EventPublisher eventPublisher,
        Factory<ApplicantBootcampHistory, ApplicantBootcampHistoryCreation> applicantBootcampHistoryFactory)
    {
        this.fileParser = fileParser;
        this.applicantRepository = applicantRepository;
        this.applicantBootcampHistoryRepository = applicantBootcampHistoryRepository;
        this.eventPublisher = eventPublisher;
        this.applicantBootcampHistoryFactory = applicantBootcampHistoryFactory;
    }

    @Transactional
    public ApplicantUploadDTO uploadApplicants(InputStreamReader file, Long bootcampId)
    {
        List<ApplicantCreationRecord> applicantCreationRecordList = fileParser
            .parse(file);

        List<String> applicantIds = new ArrayList<>();
        List<Integer> failedRows = IntStream.range(1, applicantCreationRecordList.size() + 1)
            .boxed()
            .filter((index) -> {
                ApplicantCreationRecord applicantCreationRecord = applicantCreationRecordList
                    .get(index - 1);
                Applicant currentApplicant = applicantCreationRecord.getApplicant();
                String currentEmail = currentApplicant.getEmail();

                if (applicantRepository.existsByEmail(currentEmail))
                {
                    currentApplicant = mergeAttributes(currentApplicant, applicantRepository
                        .findByEmail(currentEmail)
                        .orElseThrow(() -> new EntityNotFoundException(
                            Field.EMAIL,
                            ErrorsUtil.getDescription(
                                ErrorsUtil.ERROR_DESCRIPTION_APPLICANT_NOT_FOUND,
                                Field.EMAIL,
                                currentEmail)
                        )));
                }

                boolean hasErrors = applicantCreationRecord.isFailed();
                if (!hasErrors)
                {
                    saveOrUpdateApplicant(currentApplicant, bootcampId,
                        applicantCreationRecord.getComplementaryAttributes());
                    applicantIds.add(String.valueOf(currentApplicant.getId()));
                }

                return hasErrors;
            }).collect(Collectors.toList());
        String failedMessage = failedRows.size() > 0 ? UPLOADED_ERROR_MESSAGE : "";

        eventPublisher.publishEvent(new BootcampApplicantsUploadedEvent(bootcampId,
            applicantIds.stream().map(Long::valueOf).collect(Collectors.toSet())));
        return new ApplicantUploadDTO(applicantIds, failedMessage, failedRows);
    }

    private Applicant mergeAttributes(Applicant currentApplicant, Applicant existingApplicant)
    {
        BeanUtils.copyProperties(currentApplicant, existingApplicant,
            "id", "email", "applicantBootcampHistoryIds", "bootcampIds");
        return existingApplicant;
    }

    private void saveOrUpdateApplicant(
        Applicant currentApplicant, long bootcampId,
        List<ComplementaryAttribute> complementaryAttributes)
    {
        saveApplicantBootcampHistory(currentApplicant.getId(), bootcampId, complementaryAttributes);

        List<ApplicantBootcampHistory> applicantBootcampHistories =
            applicantBootcampHistoryRepository.findAllByApplicantId(currentApplicant.getId());

        currentApplicant.addBootcampId(bootcampId);
        currentApplicant.addApplicantBootcampHistoriesIds(
            getApplicantBootcampHistoriesIds(applicantBootcampHistories));

        applicantRepository.save(currentApplicant);
    }

    private void saveApplicantBootcampHistory(
        long applicantId, long bootcampId,
        List<ComplementaryAttribute> complementaryAttributes)
    {

        ApplicantBootcampHistory applicantBootcampHistory = applicantBootcampHistoryRepository
            .findByBootcampIdAndApplicantId(bootcampId, applicantId)
            .orElse(
                applicantBootcampHistoryFactory.create(
                    new ApplicantBootcampHistoryCreation(bootcampId, applicantId,
                        complementaryAttributes))
            );

        applicantBootcampHistory.updateComplementaryAttributes(complementaryAttributes);
        applicantBootcampHistoryRepository.save(applicantBootcampHistory);
    }

    private List<Long> getApplicantBootcampHistoriesIds(
        List<ApplicantBootcampHistory> applicantBootcampHistories)
    {
        return applicantBootcampHistories.stream()
            .map(ApplicantBootcampHistory::getApplicantBootcampHistoryId)
            .collect(Collectors.toList());
    }
}
