package com.jalasoft.bootcamp.bootcamp.infrastructure.factory.bootcamp;

import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.Bootcamp;
import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.BootcampRepository;
import com.jalasoft.bootcamp.becommon.domain.ddd.IsFactory;
import com.jalasoft.bootcamp.becommon.domain.exceptions.DuplicatedEntityException;
import com.jalasoft.bootcamp.becommon.domain.exceptions.InvalidArgumentsEntityException;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.bootcamp.BootcampDTO;
import com.jalasoft.bootcamp.becommon.infrastructure.factory.Factory;
import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;

import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.Field;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@IsFactory
@Service
@Qualifier("BootcampFactory")
public class BootcampFactory implements Factory<Bootcamp, BootcampDTO>
{
    private static final int MIN_PARTICIPANTS = 0;
    private static final int MAX_DESCRIPTION_LENGTH = 100;
    private final BootcampRepository bootcampRepository;

    @Autowired
    public BootcampFactory(final BootcampRepository bootcampRepository)
    {
        this.bootcampRepository = bootcampRepository;
    }

    @Override
    public Bootcamp create(BootcampDTO creationContext)
    {
        validateBootcampValues(creationContext);
        validateIfAlreadyExistsBootcamp(creationContext);
//       TODO(daniel.lopez): Remove workflow schema id hardcoded value 1 when use case to
//        associate workflow to a bootcamp will be implemented
        return new Bootcamp(UUID.randomUUID().getLeastSignificantBits(),
            creationContext.getName(),
            creationContext.getDescription(),
            creationContext.getLocation(),
            creationContext.getStartDate(),
            creationContext.getEndDate(), creationContext.getWorkflowType(), 1L,
            creationContext.getDepartmentType(), (long) creationContext.getDepartmentType(),
            creationContext.getParticipantsExpectedQuantity(), new HashSet<>(),
            creationContext.getProjectType(), 1L, Collections.emptySet());
    }

    private void validateIfAlreadyExistsBootcamp(final BootcampDTO creationContext)
    {
        boolean existAny = bootcampRepository
            .existsByNameIgnoreCase(creationContext.getName());
        if (existAny)
        {
            throw new DuplicatedEntityException(
                Field.NAME,
                ErrorsUtil.getDescription(
                    ErrorsUtil.ERROR_DESCRIPTION_BOOTCAMP_DUPLICATED,
                    Field.NAME,
                    String.valueOf(creationContext.getName())
                ));
        }
    }

    private void validateBootcampValues(final BootcampDTO bootcampCreation)
    {
        if (bootcampCreation.getDescription().length() > MAX_DESCRIPTION_LENGTH)
        {
            throw new InvalidArgumentsEntityException(
                Field.DESCRIPTION,
                ErrorsUtil.ERROR_DESCRIPTION_INVALID_DESCRIPTION);
        }
        if (bootcampCreation.getParticipantsExpectedQuantity() < MIN_PARTICIPANTS)
        {
            throw new InvalidArgumentsEntityException(
                Field.PARTICIPANTS_EXPECTED_QUANTITY,
                ErrorsUtil.ERROR_DESCRIPTION_INVALID_PARTICIPANTS_EXPECTED_QUANTITY);
        }
    }
}
