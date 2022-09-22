package com.jalasoft.bootcamp.becommon.infrastructure.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DateParserUtils
{
    private DateParserUtils()
    {
    }

    public static LocalDate parseStringToLocalDate(String date)
    {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        return LocalDate.parse(date, dateTimeFormatter);
    }
}
