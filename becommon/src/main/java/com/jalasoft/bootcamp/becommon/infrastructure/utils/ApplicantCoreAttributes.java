package com.jalasoft.bootcamp.becommon.infrastructure.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum ApplicantCoreAttributes
{
    BIRTHDAY(new HashSet<>(Collections.singletonList("Birthday"))),
    CAREER(new HashSet<>(Collections.singletonList("What is your career?"))),
    CURRENT_COUNTRY(new HashSet<>(Collections.singletonList("Current country"))),
    CURRENT_CITY(new HashSet<>(Collections.singletonList("Current city"))),
    DEGREE(new HashSet<>(Collections.singletonList("How far along are you in your career?"))),
    EMAIL(new HashSet<>(Collections.singletonList("Email Address"))),
    FULL_NAME(new HashSet<>(Collections.singletonList("Full name"))),
    PHONE_NUMBER(new HashSet<>(Collections.singletonList("Phone number"))),
    PHOTO(new HashSet<>(Collections.singletonList("Photo")));

    private final Set<String> value;

    ApplicantCoreAttributes(final Set<String> value)
    {
        this.value = value;
    }

    public static boolean containsCoreAttribute(String attribute)
    {
        return Arrays.stream(ApplicantCoreAttributes.values())
            .anyMatch(coreAttribute -> coreAttribute.isCoreAttribute(attribute));
    }

    public boolean isCoreAttribute(String attribute)
    {
        return value.contains(attribute);
    }

    public Set<String> getValue()
    {
        return value;
    }
}
