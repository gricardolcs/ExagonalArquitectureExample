package com.jalasoft.bootcamp.setting.domain.category;

import com.jalasoft.bootcamp.becommon.domain.ddd.IsAggregateRoot;
import com.jalasoft.bootcamp.becommon.domain.ddd.IsEntity;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

@Getter
@Setter
@IsEntity
@IsAggregateRoot
public class Category extends AbstractAggregateRoot<Category>
{
    @Id
    private final Long id;

    private String name;

    private String description;

    private Set<String> skills;

    @PersistenceConstructor
    public Category(
        final long id,
        final String name,
        String description,
        final Set<String> skills)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.skills = skills;
    }
}
