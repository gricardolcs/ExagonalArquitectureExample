package com.jalasoft.bootcamp.bootcamp.domain.status;

import com.jalasoft.bootcamp.becommon.domain.ddd.IsEntity;
import com.jalasoft.bootcamp.bootcamp.domain.workflow.Workflow;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@Getter
@IsEntity
@Entity
@Table(name = "Status")
public class Status extends AbstractAggregateRoot<Workflow>
{
    @EmbeddedId
    private StatusId id;

    @Embedded
    private StatusName name;

    public Status()
    {
    }
}
