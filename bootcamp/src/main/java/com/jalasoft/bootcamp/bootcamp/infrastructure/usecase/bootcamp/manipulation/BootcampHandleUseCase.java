package com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.bootcamp.manipulation;

import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.Bootcamp;
import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.BootcampRepository;
import com.jalasoft.bootcamp.becommon.domain.ddd.IsUseCase;
import com.jalasoft.bootcamp.becommon.domain.exceptions.DuplicatedEntityException;
import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.bootcamp.BootcampDTO;
import com.jalasoft.bootcamp.becommon.infrastructure.factory.Factory;
import com.jalasoft.bootcamp.bootcamp.infrastructure.utils.BootcampDTOBuilder;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.Field;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@IsUseCase
@Service
public class BootcampHandleUseCase
{
    private final BootcampRepository bootcampRepository;
    private final Factory<Bootcamp, BootcampDTO> bootcampFactory;
    private final BootcampDTOBuilder bootcampDTOBuilder;

    @Autowired
    public BootcampHandleUseCase(
        BootcampRepository bootcampRepository,
        Factory<Bootcamp, BootcampDTO> bootcampFactory, BootcampDTOBuilder bootcampDTOBuilder)
    {
        this.bootcampRepository = bootcampRepository;
        this.bootcampFactory = bootcampFactory;
        this.bootcampDTOBuilder = bootcampDTOBuilder;
    }

    public BootcampDTO create(final BootcampDTO creationDTO)
    {
        final Bootcamp bootcamp = bootcampFactory.create(new BootcampDTO(
            creationDTO.getName(), creationDTO.getDescription(), creationDTO.getLocation(),
            creationDTO.getStartDate(), creationDTO.getEndDate(),
            creationDTO.getWorkflowType(), creationDTO.getDepartmentType(),
            creationDTO.getParticipantsExpectedQuantity(),
            creationDTO.getAcceptedParticipants(), creationDTO.getActualParticipants(),
            creationDTO.getProjectType()));

        Bootcamp savedBootcamp = bootcampRepository.save(bootcamp);
        return bootcampDTOBuilder.build(savedBootcamp);
    }

    public BootcampDTO edit(final Long bootcampId, final BootcampDTO bootcampEditionDTO)
    {
        String bootcampName = bootcampEditionDTO.getName();
        validateIfBootcampNameExists(bootcampId, bootcampName);
        Bootcamp bootcamp = bootcampRepository.findById(bootcampId)
            .orElseThrow(() -> new EntityNotFoundException(
                Field.ID,
                ErrorsUtil.getDescription(
                    ErrorsUtil.ERROR_DESCRIPTION_BOOTCAMP_NOT_FOUND,
                    Field.ID,
                    String.valueOf(bootcampId))
            ));
        bootcamp.changeName(bootcampName);
        bootcamp.changeDescription(bootcampEditionDTO.getDescription());
        bootcamp.changeStartDate(bootcampEditionDTO.getStartDate());
        bootcamp.changeEndDate(bootcampEditionDTO.getEndDate());
        bootcamp.changeWorkflowType(bootcampEditionDTO.getWorkflowType());
        bootcamp.changeDepartment(bootcampEditionDTO.getDepartmentType());
        bootcamp.changeBootcampParticipantsExpectedQuantity(
            bootcampEditionDTO.getParticipantsExpectedQuantity());
        bootcamp.changeProjectType(bootcampEditionDTO.getProjectType());
        Bootcamp savedBootcamp = bootcampRepository.save(bootcamp);
        return bootcampDTOBuilder.build(savedBootcamp);
    }

    public Bootcamp delete(final Long id)
    {
        Bootcamp bootcamp = bootcampRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                Field.ID,
                ErrorsUtil.getDescription(
                    ErrorsUtil.ERROR_DESCRIPTION_BOOTCAMP_NOT_FOUND,
                    Field.ID,
                    String.valueOf(id))
            ));

        bootcampRepository.deleteById(id);
        return bootcamp;
    }


    private void validateIfBootcampNameExists(
        final Long bootcampId,
        final String bootcampName)
    {
        boolean existAny = bootcampRepository
            .existsByNameIgnoreCaseAndIdIsNot(bootcampName, bootcampId);
        if (existAny)
        {
            throw new DuplicatedEntityException(
                Field.NAME,
                ErrorsUtil.getDescription(
                    ErrorsUtil.ERROR_DESCRIPTION_BOOTCAMP_DUPLICATED,
                    Field.NAME,
                    String.valueOf(bootcampName)
                ));
        }
    }
}
