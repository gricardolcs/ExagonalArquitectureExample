package com.jalasoft.bootcamp.bootcamp.domain.status;

import com.jalasoft.bootcamp.becommon.domain.ddd.IsValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@IsValueObject
@Embeddable
public class StatusName
{
    @Column(unique = true, nullable = false)
    private String name;

    public StatusName()
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
        StatusName that = (StatusName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name);
    }
}
