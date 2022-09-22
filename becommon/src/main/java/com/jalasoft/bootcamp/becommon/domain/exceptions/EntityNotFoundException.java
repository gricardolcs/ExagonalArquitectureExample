package com.jalasoft.bootcamp.becommon.domain.exceptions;

import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.http.HttpStatus;

@Getter
public class EntityNotFoundException extends BaseException
{

    private static final int httpStatus = HttpStatus.SC_NOT_FOUND;

    private static final Logger logger = LogManager.getLogger(EntityNotFoundException.class);

    public EntityNotFoundException(List<Errors> errorsList)
    {
        super(ErrorsUtil.ERROR_MESSAGE_ENTITY_NOT_FOUND, httpStatus, errorsList);
        logger.error(ErrorsUtil.ERROR_MESSAGE_ENTITY_NOT_FOUND);
    }

    public EntityNotFoundException(String fieldId, String errorDescription)
    {
        super(ErrorsUtil.ERROR_MESSAGE_ENTITY_NOT_FOUND,
            httpStatus,
            Arrays.asList(new Errors(fieldId, errorDescription)));
        logger.error(ErrorsUtil.ERROR_MESSAGE_ENTITY_NOT_FOUND);
    }
}
