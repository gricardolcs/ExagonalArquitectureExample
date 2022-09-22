package com.jalasoft.bootcamp.bootcamp.domain.workflow;

import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.Bootcamp;
import com.jalasoft.bootcamp.becommon.domain.ddd.IsEntity;
import com.jalasoft.bootcamp.bootcamp.domain.stage.Stage;
import java.util.List;
import java.util.Optional;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.data.domain.AbstractAggregateRoot;

@IsEntity
@Entity
@Table(name = "workflow")
public class Workflow extends AbstractAggregateRoot<Workflow>
{
    @Id
    private Long id;

    @Embedded
    private WorkflowName name;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "workflowId", referencedColumnName = "id")
    private List<Bootcamp> bootcampList;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "workflowId", referencedColumnName = "id")
    private List<Stage> stages;

    /**
     * JPA usage only
     */
    protected Workflow()
    {
    }

    public Workflow(Long id, WorkflowName name, List<Stage> stages)
    {
        this.id = id;
        this.name = name;
        this.stages = stages;
    }

    public Long getId()
    {
        return id;
    }

    public WorkflowName getName()
    {
        return name;
    }

    public List<Stage> getStages()
    {
        return stages;
    }

    public void changeName(WorkflowName name)
    {
        this.name = name;
    }

    public Optional<Stage> getStageOf(Long stageId)
    {
        return stages.stream().filter(stage -> stage.getId().equals(stageId)).findFirst();
    }
}
