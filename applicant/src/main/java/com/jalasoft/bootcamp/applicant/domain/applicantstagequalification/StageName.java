package com.jalasoft.bootcamp.applicant.domain.applicantstagequalification;

import com.jalasoft.bootcamp.becommon.domain.ddd.IsValueObject;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@IsValueObject
public class StageName
{
    private String name;

    /*
     * The override is necessary for unit tests performed with the Mockito tool, this allows
     * to compare objects through the tests
     */
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
        StageName stageName1 = (StageName) o;
        return Objects.equals(name, stageName1.name);
    }

    /*
     * Overriding is required to set object's hash code unique given its
     * , mockito tests usage
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(name);
    }
}
