package com.jalasoft.bootcamp.bootcamp.domain.projecttype;

import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.Bootcamp;
import com.jalasoft.bootcamp.becommon.domain.ddd.IsEntity;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.data.domain.AbstractAggregateRoot;

@IsEntity
@Entity
@Table(name = "ProjectType")
public class ProjectType extends AbstractAggregateRoot<ProjectType>
{
    @Id
    private Long id;

    @Embedded
    private ProjectTypeName name;

    @OneToMany
    @JoinColumn(name = "projectTypeId", referencedColumnName = "id")
    private List<Bootcamp> bootcampList;

    public ProjectType()
    {
    }

    public ProjectType(Long id, ProjectTypeName name)
    {
        this.id = id;
        this.name = name;
    }

    public Long getId()
    {
        return id;
    }

    public ProjectTypeName getName()
    {
        return name;
    }
}
