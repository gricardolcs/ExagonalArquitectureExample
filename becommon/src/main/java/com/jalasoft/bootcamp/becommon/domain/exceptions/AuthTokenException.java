package com.jalasoft.bootcamp.becommon.domain.exceptions;

import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import java.util.Arrays;
import java.util.List;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthTokenException extends BaseException
{
    private static final int httpStatus = HttpStatus.SC_FORBIDDEN;
    private static final Logger logger = LogManager.getLogger(AuthTokenException.class);

    public AuthTokenException(List<Errors> errorsList)
    {
        super(ErrorsUtil.ERROR_MESSAGE_FILE_INVALID, httpStatus, errorsList);
        logger.error(ErrorsUtil.ERROR_MESSAGE_FILE_INVALID);
    }

    public AuthTokenException(String field, String errorDescription)
    {
        super(
            ErrorsUtil.ERROR_MESSAGE_AUTHENTICATION_TOKEN,
            httpStatus,
            Arrays.asList(new Errors(field, errorDescription)));
        logger.error(ErrorsUtil.ERROR_MESSAGE_AUTHENTICATION_TOKEN);
    }
}
