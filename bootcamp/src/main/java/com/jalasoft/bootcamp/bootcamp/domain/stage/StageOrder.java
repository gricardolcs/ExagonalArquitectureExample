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
public class StageOrder
{
    @Column(name = "stageOrder", nullable = false)
    private int order;

    /**
     * JPA usage only
     */
    public StageOrder()
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
        StageOrder that = (StageOrder) o;
        return order == that.order;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(order);
    }
}
