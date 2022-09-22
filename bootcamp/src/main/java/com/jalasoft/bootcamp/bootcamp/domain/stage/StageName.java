package com.jalasoft.bootcamp.bootcamp.domain.stage;

import com.jalasoft.bootcamp.becommon.domain.ddd.IsValueObject;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@IsValueObject
@Embeddable
public class StageName
{
    @Column(nullable = false)
    private String name;

    /*
     * JPA usage only
     */
    protected StageName()
    {
    }

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
        StageName stageName = (StageName) o;
        return Objects.equals(name, stageName.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name);
    }
}
