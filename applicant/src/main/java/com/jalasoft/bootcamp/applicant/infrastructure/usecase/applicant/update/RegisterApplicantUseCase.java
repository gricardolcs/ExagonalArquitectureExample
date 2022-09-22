package com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.update;

import com.jalasoft.bootcamp.applicant.domain.applicant.Applicant;
import com.jalasoft.bootcamp.applicant.domain.applicant.ApplicantRepository;
import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantRegisteredAtBootcampsDTO;
import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantRegisteredInBootcampDTO;
import com.jalasoft.bootcamp.applicant.infrastructure.eventpublisher.EventPublisher;
import com.jalasoft.bootcamp.applicant.infrastructure.events.BootcampApplicantsUploadedEvent;
import com.jalasoft.bootcamp.becommon.domain.ddd.IsUseCase;
import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@IsUseCase
@Service
public class RegisterApplicantUseCase
{
    private static String BOOTCAMP_SERVICE_URL;
    private final ApplicantRepository applicantRepository;
    private final EventPublisher eventPublisher;
    private final RestTemplate restTemplate;

    @Autowired
    public RegisterApplicantUseCase(
        ApplicantRepository applicantRepository,
        EventPublisher eventPublisher, RestTemplate restTemplate)
    {
        this.applicantRepository = applicantRepository;
        this.eventPublisher = eventPublisher;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public ApplicantRegisteredAtBootcampsDTO registerApplicantInBootcamps(
        Long applicantId, List<Long> bootcampIds)
    {
        Optional<Applicant> applicantOptional = applicantRepository.findById(applicantId);
        Set<Long> registeredApplicantInBootcamps = new HashSet<>();
        if (applicantOptional.isPresent())
        {
            bootcampIds.forEach(bootcampId -> {
                validateBootcamp(bootcampId);
                registerApplicant(applicantOptional.get(), bootcampId);
                registeredApplicantInBootcamps.add(bootcampId);
            });
            if (!registeredApplicantInBootcamps.isEmpty())
            {
                publishApplicantUploaded(registeredApplicantInBootcamps, Set.of(applicantId));
            }
        }
        return new ApplicantRegisteredAtBootcampsDTO(registeredApplicantInBootcamps);
    }

    @Transactional
    public ApplicantRegisteredInBootcampDTO registerApplicantsInBootcamp(
        List<Long> applicantIds, Long bootcampId)
    {
        validateBootcamp(bootcampId);
        Set<Long> registeredApplicantsInBootcamp = new HashSet<>();
        applicantIds.forEach(applicantId -> {
            Optional<Applicant> applicantOptional = applicantRepository.findById(applicantId);
            if (applicantOptional.isPresent())
            {
                registerApplicant(
                    applicantOptional.get(),
                    bootcampId);
                registeredApplicantsInBootcamp.add(applicantOptional.get().getId());
            }
        });
        publishApplicantUploaded(
            Set.of(bootcampId),
            registeredApplicantsInBootcamp);
        return new ApplicantRegisteredInBootcampDTO(registeredApplicantsInBootcamp);
    }

    private void registerApplicant(final Applicant applicant, final Long bootcampId)
    {
        applicant.addBootcampId(bootcampId);
        applicantRepository.save(applicant);
    }

    private void validateBootcamp(final Long bootcampId)
    {
        try
        {
            restTemplate.getForEntity(BOOTCAMP_SERVICE_URL + "bootcamp/{bootcampId}",
                Object.class, Map.of("bootcampId", bootcampId));
        }
        catch (HttpClientErrorException e)
        {
            throw new EntityNotFoundException(
                Field.ID,
                ErrorsUtil.getDescription(
                    ErrorsUtil.ERROR_DESCRIPTION_BOOTCAMP_NOT_FOUND,
                    Field.ID,
                    String.valueOf(bootcampId)));
        }
    }

    private void publishApplicantUploaded(final Set<Long> bootcampToRegister,
        final Set<Long> applicantIds)
    {
        if (!bootcampToRegister.isEmpty()) {
            for (Long bootcampId : bootcampToRegister) {
                eventPublisher.publishEvent(new BootcampApplicantsUploadedEvent(
                    bootcampId,
                    applicantIds));
            }
        }
    }

    @Value("${bootcamp.service.url}")
    private void setBootcampServiceUrl(String bootcampServiceUrl)
    {
        RegisterApplicantUseCase.BOOTCAMP_SERVICE_URL = bootcampServiceUrl;
    }
}
