package com.jalasoft.bootcamp.bootcamp.domain.stage.schema;

import com.jalasoft.bootcamp.becommon.domain.ddd.IsValueObject;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@IsValueObject
@Embeddable
public class InputFieldId implements Serializable
{
    private long id;

    /*
     * JPA usage only
     */
    protected InputFieldId()
    {
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof InputFieldId))
        {
            return false;
        }
        InputFieldId inputFieldId = (InputFieldId) o;
        return getId() == inputFieldId.getId();
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId());
    }
}
