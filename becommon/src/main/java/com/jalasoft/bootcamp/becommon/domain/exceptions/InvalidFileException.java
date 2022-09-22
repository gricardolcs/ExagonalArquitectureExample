package com.jalasoft.bootcamp.becommon.domain.exceptions;

import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.http.HttpStatus;

@Getter
public class InvalidFileException extends BaseException
{

    private static final int httpStatus = HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE;

    private static final Logger logger = LogManager.getLogger(InvalidFileException.class);

    public InvalidFileException(List<Errors> errorsList)
    {
        super(ErrorsUtil.ERROR_MESSAGE_FILE_INVALID, httpStatus, errorsList);
        logger.error(ErrorsUtil.ERROR_MESSAGE_FILE_INVALID);
    }

    public InvalidFileException(String fieldId, String errorDescription)
    {
        super(ErrorsUtil.ERROR_MESSAGE_FILE_INVALID,
            httpStatus,
            Arrays.asList(new Errors(fieldId, errorDescription)));
        logger.error(ErrorsUtil.ERROR_MESSAGE_FILE_INVALID);
    }

}
