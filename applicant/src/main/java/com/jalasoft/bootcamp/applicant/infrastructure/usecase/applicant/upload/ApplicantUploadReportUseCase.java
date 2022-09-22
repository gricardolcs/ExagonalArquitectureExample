package com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.upload;

import com.jalasoft.bootcamp.applicant.domain.applicant.Applicant;
import com.jalasoft.bootcamp.applicant.domain.applicant.ApplicantRepository;
import com.jalasoft.bootcamp.applicant.domain.applicant.Report;
import com.jalasoft.bootcamp.becommon.domain.ddd.IsUseCase;
import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.becommon.domain.exceptions.InvalidFileException;
import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantReportDTO;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.Field;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@IsUseCase
@Service
public class ApplicantUploadReportUseCase
{
    private final ApplicantRepository applicantRepository;

    @Autowired
    public ApplicantUploadReportUseCase(final ApplicantRepository applicantRepository)
    {
        this.applicantRepository = applicantRepository;
    }

    @Transactional
    public ApplicantReportDTO uploadReport(MultipartFile file, long applicantId, long bootcampId)
    throws IOException
    {
        String nameFile = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(nameFile);
        long size = file.getSize();
        byte[] inputFile;
        Applicant applicant;
        if (isValidExtension(extension) && verifySize(size))
        {
            applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new EntityNotFoundException(
                            Field.ID,
                            ErrorsUtil.getDescription(
                                ErrorsUtil.ERROR_DESCRIPTION_APPLICANT_NOT_FOUND,
                                Field.ID,
                                String.valueOf(applicantId))
                ));
            inputFile = file.getBytes();
            List<Report> reports = new ArrayList<>();
            reports.add(new Report(bootcampId, nameFile, inputFile));
            applicant.setReports(reports);
            applicantRepository.save(applicant);
        }
        else
        {
            throw new InvalidFileException(file.getOriginalFilename(), ErrorsUtil.ERROR_DESCRIPTION_FILE_INVALID_TYPE_OR_SIZE);
        }
        return new ApplicantReportDTO(applicant);
    }

    private boolean verifySize(long size)
    {
        return size <= 3000000;
    }

    private boolean isValidExtension(String extension)
    {
        Map<Integer, String> extensions = new HashMap<>();
        extensions.put(1, "txt");
        extensions.put(2, "docx");
        extensions.put(3, "pdf");
        extensions.put(4, "png");
        extensions.put(5, "jpg");
        return extensions.containsValue(extension);
    }
}
