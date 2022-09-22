package com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.applicantBootcamp;

import com.jalasoft.bootcamp.becommon.domain.ddd.IsUseCase;
import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.Bootcamp;
import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.BootcampRepository;
import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.bootcamp.BootcampDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.utils.BootcampDTOBuilder;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@IsUseCase
@Service
public class ApplicantBootcampFetchAllUseCase
{
    private final BootcampRepository bootcampRepository;
    private final BootcampDTOBuilder bootcampDTOBuilder;

    @Autowired
    public ApplicantBootcampFetchAllUseCase(BootcampRepository bootcampRepository,
        BootcampDTOBuilder bootcampDTOBuilder) {
        this.bootcampRepository = bootcampRepository;
        this.bootcampDTOBuilder = bootcampDTOBuilder;
    }

    public List<BootcampDTO> getAllByApplicantId(Long applicantId) {
        List<Bootcamp> bootcamps = this.bootcampRepository.findAllByBootcampMembersContaining(applicantId);
        if (!bootcamps.isEmpty()) {
            return bootcamps.stream()
                    .map(bootcamp -> bootcampDTOBuilder.build(bootcamp))
                    .collect(Collectors.toList());
        }
        throw new EntityNotFoundException(
            Field.APPLICANT_ID,
            ErrorsUtil.getDescription(
                ErrorsUtil.ERROR_DESCRIPTION_APPLICANT_NOT_FOUND,
                Field.APPLICANT_ID,
                String.valueOf(applicantId)
            ));
    }
}
