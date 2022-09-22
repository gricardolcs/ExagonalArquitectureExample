package com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.fetch;

import com.jalasoft.bootcamp.applicant.domain.applicant.Applicant;
import com.jalasoft.bootcamp.applicant.domain.applicant.ApplicantRepository;
import com.jalasoft.bootcamp.applicant.domain.applicant.Report;
import com.jalasoft.bootcamp.becommon.domain.ddd.IsUseCase;
import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantDownloadReportDTO;

import java.util.List;
import java.util.Optional;

import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.Field;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@IsUseCase
@Service
public class ApplicantReportFetchUseCase
{
    private final ApplicantRepository applicantRepository;

    @Autowired
    public ApplicantReportFetchUseCase(ApplicantRepository applicantRepository)
    {
        this.applicantRepository = applicantRepository;
    }

    public ApplicantDownloadReportDTO getApplicantFile(long applicantId, long bootcampId)
    {
        byte[] applicantReport = null;
        String fileName = "";
        long bootcampIdFound = 0;
        Applicant applicant = applicantRepository.findById(applicantId)
            .orElseThrow(() -> new EntityNotFoundException(
                    Field.ID,
                    ErrorsUtil.getDescription(
                        ErrorsUtil.ERROR_DESCRIPTION_APPLICANT_NOT_FOUND,
                        Field.ID, String.valueOf(applicantId))
            ));
        List<Report> reports = applicant.getReports();
        Optional<Report> reportOptional = reports
            .stream()
            .filter(report -> report.getBootcampId() == bootcampId).findFirst();
        if (reportOptional.isPresent())
        {
            Report reportFound = reportOptional.get();
            applicantReport = reportFound.getReport();
            fileName = reportFound.getFileName();
            bootcampIdFound = reportFound.getBootcampId();
        }
        return new ApplicantDownloadReportDTO(bootcampIdFound, fileName, applicantReport);
    }
}
