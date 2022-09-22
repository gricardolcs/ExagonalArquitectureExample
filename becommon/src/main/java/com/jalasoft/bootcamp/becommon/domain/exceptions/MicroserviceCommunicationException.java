package com.jalasoft.bootcamp.becommon.domain.exceptions;

import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import java.util.Arrays;
import lombok.Getter;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Getter
public class MicroserviceCommunicationException extends BaseException
{
    private static final int HTTP_STATUS = HttpStatus.SC_PARTIAL_CONTENT;

    private static final Logger logger = LogManager.getLogger(MicroserviceCommunicationException.class);

    public MicroserviceCommunicationException(
        final String microservice)
    {
        super(ErrorsUtil.ERROR_MICROSERVICE_COMMUNICATION, HTTP_STATUS,
            Arrays.asList(new Errors(microservice,
                String.format(ErrorsUtil.ERROR_CERTAIN_MICROSERVICE_COMMUNICATION, microservice))));
        logger.error(String.format(ErrorsUtil.ERROR_CERTAIN_MICROSERVICE_COMMUNICATION, microservice));
    }
}
