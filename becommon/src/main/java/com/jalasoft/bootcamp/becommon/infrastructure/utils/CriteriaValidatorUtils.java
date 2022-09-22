package com.jalasoft.bootcamp.becommon.infrastructure.utils;

import com.jalasoft.bootcamp.becommon.domain.exceptions.InvalidQueryParamValueException;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CriteriaValidatorUtils
{
    private static final String REGEX_CRITERIA_VALID_CHARACTERS = "([A-Za-z\\u00C0-\\u00FF][\\s"
        + "]*)+";
    private static final Pattern PATTERN_CRITERIA = Pattern.compile(
        REGEX_CRITERIA_VALID_CHARACTERS);

    public static void validateCriteria(String criteria)
    {
        Matcher matcher = PATTERN_CRITERIA.matcher(criteria);
        if(!matcher.matches()) {
            throw new InvalidQueryParamValueException(
                Field.FULL_NAME,
                String.format(
                    ErrorsUtil.ERROR_DESCRIPTION_INVALID_QUERY_PARAM,
                    Field.FULL_NAME));
        }
    }
}
