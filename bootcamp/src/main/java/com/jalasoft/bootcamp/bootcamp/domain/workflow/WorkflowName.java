package com.jalasoft.bootcamp.bootcamp.domain.workflow;

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
public class WorkflowName
{
    @Column(unique = true, nullable = false)
    private String name;

    /*
     * JPA usage only
     */
    public WorkflowName()
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
        WorkflowName that = (WorkflowName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name);
    }
}
