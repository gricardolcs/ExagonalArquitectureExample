package com.jalasoft.bootcamp.becommon.domain.exceptions;

import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.http.HttpStatus;

public class BadRequestException extends BaseException
{

    private static final int httpStatus = HttpStatus.SC_CONFLICT;
    private static final Logger logger = LogManager.getLogger(BadRequestException.class);

    public BadRequestException(String errorDescription)
    {
        super(
            ErrorsUtil.ERROR_MESSAGE_BAD_REQUEST,
            httpStatus,
            Arrays.asList(new Errors(null, errorDescription)));
        logger.error(ErrorsUtil.ERROR_MESSAGE_BAD_REQUEST);
    }
}