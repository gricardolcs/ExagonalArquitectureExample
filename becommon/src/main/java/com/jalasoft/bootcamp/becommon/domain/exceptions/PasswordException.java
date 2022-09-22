package com.jalasoft.bootcamp.becommon.domain.exceptions;

import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import java.util.Arrays;
import lombok.Getter;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Getter
public class PasswordException extends BaseException
{
    private static final int HTTP_STATUS = HttpStatus.SC_BAD_REQUEST;

    private static final Logger logger = LogManager.getLogger(PasswordException.class);

    public PasswordException(String field, String error)
    {
        super(ErrorsUtil.ERROR_PASSWORD_CHANGE_INVALID_PARAMETERS, HTTP_STATUS,
            Arrays.asList(new Errors(field, error)));
        logger.error(ErrorsUtil.ERROR_PASSWORD_CHANGE_INVALID_PARAMETERS);
    }
}
