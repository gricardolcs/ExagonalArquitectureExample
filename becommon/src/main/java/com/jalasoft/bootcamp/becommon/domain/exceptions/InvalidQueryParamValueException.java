package com.jalasoft.bootcamp.becommon.domain.exceptions;

import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.http.HttpStatus;

@Getter
public class InvalidQueryParamValueException extends BaseException
{

    private static final int httpStatus = HttpStatus.SC_BAD_REQUEST;

    private static final Logger logger = LogManager.getLogger(InvalidQueryParamValueException.class);

    public InvalidQueryParamValueException(List<Errors> errorsList)
    {
        super(ErrorsUtil.ERROR_MESSAGE_INVALID_QUERY_PARAM, httpStatus, errorsList);
        logger.error(ErrorsUtil.ERROR_MESSAGE_INVALID_QUERY_PARAM);
    }

    public InvalidQueryParamValueException(String fieldId, String errorDescription)
    {
        super(ErrorsUtil.ERROR_MESSAGE_INVALID_QUERY_PARAM,
            httpStatus,
            Arrays.asList(new Errors(fieldId, errorDescription)));
        logger.error(ErrorsUtil.ERROR_MESSAGE_INVALID_QUERY_PARAM);
    }
}
