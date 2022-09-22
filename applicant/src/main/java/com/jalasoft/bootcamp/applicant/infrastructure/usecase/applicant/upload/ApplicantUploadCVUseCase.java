package com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.upload;

import com.jalasoft.bootcamp.applicant.domain.applicant.Applicant;
import com.jalasoft.bootcamp.applicant.domain.applicant.ApplicantRepository;
import com.jalasoft.bootcamp.applicant.domain.applicant.CurriculumVitae;
import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantDTO;
import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.becommon.domain.exceptions.InvalidFileException;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.Field;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ApplicantUploadCVUseCase
{
    private static String SETTINGS_SERVICE_URL;

    private final ApplicantRepository applicantRepository;
    private final RestTemplate restTemplate;

    private static final Logger logger = LogManager.getLogger(ApplicantUploadCVUseCase.class);


    @Autowired
    public ApplicantUploadCVUseCase(
        final ApplicantRepository applicantRepository,
        final RestTemplate restTemplate)
    {
        this.applicantRepository = applicantRepository;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public ApplicantDTO uploadCV(long applicantId, MultipartFile file)
    throws IOException
    {
        String nameFile = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(nameFile);
        Applicant applicant;
        if (isValidExtension(extension))
        {
            applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new EntityNotFoundException(
                    Field.ID,
                    ErrorsUtil.getDescription(
                        ErrorsUtil.ERROR_DESCRIPTION_APPLICANT_NOT_FOUND,
                        Field.ID,
                        String.valueOf(applicantId))
                ));
            String textContent = getTextFromDocument(file, extension);
            applicant.setCurriculumVitae(new CurriculumVitae(
                file.getBytes(),
                nameFile,
                textContent));
            Applicant applicantSkills = setSkills(applicant);
            applicantRepository.save(applicantSkills);
        }
        else
        {
            throw new InvalidFileException(
                file.getOriginalFilename(),
                ErrorsUtil.ERROR_DESCRIPTION_FILE_INVALID_EXTENSION);
        }
        return new ApplicantDTO(applicant);
    }

    private String getTextFromDocument(final MultipartFile file, final String extension)
    throws IOException
    {
        return extension.equals("docx") ? getDocxText(file) : getPdfText(file);
    }

    private boolean isValidExtension(final String extension)
    {
        Map<Integer, String> extensions = new HashMap<>();
        extensions.put(1, "docx");
        extensions.put(2, "pdf");
        return extensions.containsValue(extension);
    }

    private String getPdfText(final MultipartFile file) throws IOException
    {
        try (PDDocument document = PDDocument.load(file.getBytes()))
        {
            if (!document.isEncrypted())
            {
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);
                PDFTextStripper tStripper = new PDFTextStripper();
                String pdfFileInText = tStripper.getText(document);
                return getCleanText(pdfFileInText);
            } else {
                logger.error("File is encrypted!");
                return "";
            }
        }
    }

    private String getDocxText(final MultipartFile file) throws IOException
    {
        try (XWPFDocument doc = new XWPFDocument(file.getInputStream()))
        {
            XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(doc);
            String docText = xwpfWordExtractor.getText();
            return getCleanText(docText);
        }
    }

    private String getCleanText(final String text)
    {
        List<String> lines = List.of(text.split("\\r?\\n?\\s"));
        return lines.stream().map(String::trim).filter(Predicate.not(String::isEmpty))
            .collect(Collectors.joining(" "));
    }

    public Applicant setSkills(final Applicant applicant)
    {
        try
        {
            List<String> definedSkills = restTemplate.getForEntity(SETTINGS_SERVICE_URL + "skills",
                List.class).getBody();
            String cvContent = applicant.getCurriculumVitae().getContent();
            applicant.setSkills(definedSkills.stream().filter(skill -> cvContent.contains(skill))
                .collect(Collectors.toList()));

            return applicant;
        }
        catch (HttpClientErrorException e)
        {
            throw new EntityNotFoundException(
                Field.ID,
                ErrorsUtil.ERROR_DESCRIPTION_CATEGORY_SKILLS_NOT_FOUND);
        }
    }

    @Value("${settings.service.url}")
    private void setBootcampServiceUrl(String bootcampServiceUrl)
    {
        ApplicantUploadCVUseCase.SETTINGS_SERVICE_URL = bootcampServiceUrl;
    }
}