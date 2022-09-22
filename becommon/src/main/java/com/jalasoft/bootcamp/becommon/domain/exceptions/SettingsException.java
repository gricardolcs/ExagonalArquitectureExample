package com.jalasoft.bootcamp.becommon.domain.exceptions;

import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import java.util.Arrays;
import lombok.Getter;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Getter
public class SettingsException extends BaseException
{
    private static final int HTTP_STATUS = HttpStatus.SC_BAD_REQUEST;

    private static final Logger logger = LogManager.getLogger(SettingsException.class);
    public SettingsException()
    {
        super(ErrorsUtil.ERROR_DESCRIPTION_DEFAULT_ACCOUNT_SETTING_NOT_FOUND, HTTP_STATUS, Arrays.asList(new Errors("settings",
            ErrorsUtil.ERROR_DESCRIPTION_DEFAULT_ACCOUNT_SETTING_NOT_FOUND)));
        logger.error(ErrorsUtil.ERROR_DESCRIPTION_DEFAULT_ACCOUNT_SETTING_NOT_FOUND);
    }
}
