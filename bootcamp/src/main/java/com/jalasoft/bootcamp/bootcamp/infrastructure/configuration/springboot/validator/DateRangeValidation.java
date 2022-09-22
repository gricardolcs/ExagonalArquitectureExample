package com.jalasoft.bootcamp.bootcamp.infrastructure.configuration.springboot.validator;

import com.jalasoft.bootcamp.becommon.domain.exceptions.Errors;
import com.jalasoft.bootcamp.becommon.domain.exceptions.InvalidArgumentsEntityException;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.Field;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import org.springframework.beans.BeanWrapperImpl;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.util.Arrays;

public class DateRangeValidation implements ConstraintValidator<DateRange, Object>
{

    private String startDate;
    private String endDate;

    @Override
    public void initialize(DateRange constraintAnnotation)
    {
        this.startDate = constraintAnnotation.startDate();
        this.endDate = constraintAnnotation.endDate();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context)
    {
        try
        {
            LocalDate start = (LocalDate) new BeanWrapperImpl(value).getPropertyValue(startDate);
            LocalDate end = (LocalDate) new BeanWrapperImpl(value).getPropertyValue(endDate);
            return start != null && end != null && !start.isAfter(end);
        }
        catch (NullPointerException exception)
        {
            throw new InvalidArgumentsEntityException(
                Field.DATE,
                String.format(ErrorsUtil.ERROR_DESCRIPTION_INVALID_ARGUMENT, Field.DATE));
        }
    }
}
