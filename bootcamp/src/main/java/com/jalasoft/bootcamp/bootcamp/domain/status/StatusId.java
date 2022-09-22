package com.jalasoft.bootcamp.bootcamp.domain.status;

import com.jalasoft.bootcamp.becommon.domain.ddd.IsValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@IsValueObject
@Embeddable
public class StatusId implements Serializable
{
    private long id;

    protected StatusId()
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
        StatusId statusId = (StatusId) o;
        return id == statusId.id;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
