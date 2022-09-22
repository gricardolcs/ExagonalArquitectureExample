package com.jalasoft.bootcamp.bootcamp.domain.stage;

import com.jalasoft.bootcamp.becommon.domain.ddd.IsEntity;
import com.jalasoft.bootcamp.bootcamp.domain.stage.schema.InputField;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@IsEntity
@Entity
@Table(name = "stage")
public class Stage
{
    @Id
    private Long id;

    @Embedded
    private StageName stageName;

    @Embedded
    private StageOrder stageOrder;

    @Column
    private boolean showComments;

    @Column(name = "workflowId")
    @AttributeOverride(name = "id", column = @Column(name = "workflowId", nullable = false))
    private Long workflowId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "stageId", referencedColumnName = "id")
    private List<InputField> inputFields;

    @Column
    private boolean isEnglishType;

    @Column
    private boolean isContractType;

    /**
     * JPA usage only
     */
    protected Stage()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public StageName getStageName()
    {
        return stageName;
    }

    public StageOrder getStageOrder()
    {
        return stageOrder;
    }

    public boolean showComments()
    {
        return showComments;
    }

    public Long getWorkflowId()
    {
        return workflowId;
    }

    public List<InputField> getSchemas()
    {
        return inputFields;
    }

    public boolean isEnglishType()
    {
        return isEnglishType;
    }

    public boolean isContractType()
    {
        return isContractType;
    }
}
