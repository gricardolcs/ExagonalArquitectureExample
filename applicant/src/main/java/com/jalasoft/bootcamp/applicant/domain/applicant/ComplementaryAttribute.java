package com.jalasoft.bootcamp.applicant.domain.applicant;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ComplementaryAttribute
{
    private final String label;
    private final String value;

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        ComplementaryAttribute that = (ComplementaryAttribute) o;
        return Objects.equals(label, that.label) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(label, value);
    }
}
