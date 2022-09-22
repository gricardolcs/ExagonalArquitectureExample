package com.jalasoft.bootcamp.bootcamp.domain.applicantworkflow;

import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.ApplicantStageQualification;
import com.jalasoft.bootcamp.becommon.domain.ddd.IsAggregateRoot;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;

@AllArgsConstructor
@Getter
@IsAggregateRoot
@Entity
@Table(name = "applicantWorkflow")
public class ApplicantWorkflow extends AbstractAggregateRoot<ApplicantWorkflow>
{
    @Id
    private Long id;

    @Column
    @AttributeOverride(name = "id", column = @Column(name = "bootcampId", nullable = false))
    private Long bootcampId;

    @Column
    @AttributeOverride(name = "id", column = @Column(name = "applicantId", nullable = false))
    private Long applicantId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "applicantWorkflowId", referencedColumnName = "id")
    private Set<ApplicantStageQualification> applicantStageQualificationList;

    /**
     * JPA usage only
     */
    protected ApplicantWorkflow()
    {
    }

    public void addApplicantStageQualification(
        final ApplicantStageQualification applicantStageQualification)
    {
        this.applicantStageQualificationList.add(applicantStageQualification);
    }
}
