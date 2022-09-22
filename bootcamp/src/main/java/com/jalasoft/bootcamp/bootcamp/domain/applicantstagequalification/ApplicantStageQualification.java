package com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification;

import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield.QualificationField;
import com.jalasoft.bootcamp.bootcamp.domain.applicantstagequalification.qualificationfield.QualificationFieldSchema;
import com.jalasoft.bootcamp.bootcamp.domain.stage.Stage;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Entity
@Table(name = "applicantStageQualification")
public class ApplicantStageQualification
{
    @Id
    private Long id;

    @Embedded
    private ApplicantStageQualificationComment comment;

    @Embedded
    private ApplicantStageQualificationAppliedDate appliedDate;

    @Embedded
    private ApplicantStageQualificationSubmitDate submitDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stageId", referencedColumnName = "id", nullable = false)
    private Stage stage;

    @Column(name = "applicantWorkflowId")
    @AttributeOverride(name = "id", column = @Column(name = "applicantWorkflowId"))
    private Long applicantWorkflowId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "applicantStageQualificationId", referencedColumnName = "id")
    private List<QualificationField> qualificationFields;

    @Enumerated(EnumType.STRING)
    @Column
    private QualificationStatus qualificationStatus;

    /**
     * JPA usage only
     */
    protected ApplicantStageQualification()
    {
    }

    public void changeComment(ApplicantStageQualificationComment comment)
    {
        this.comment = comment;
    }

    public ApplicantStageQualification changeQualificationFieldSchemas(
        List<QualificationFieldSchema> qualificationFieldSchemas)
    {
        for (int index = 0; index < qualificationFields.size(); index++)
        {
            QualificationField qualificationField = qualificationFields.get(index);
            qualificationField.changeQualificationField(qualificationFieldSchemas.get(index));
        }
        return this;
    }

    public ApplicantStageQualification changeQualificationStatus(
        QualificationStatus qualificationStatus)
    {
        this.qualificationStatus = qualificationStatus;
        return this;
    }

    public ApplicantStageQualification changeAppliedDate(ApplicantStageQualificationAppliedDate appliedDate)
    {
        this.appliedDate = appliedDate;
        return this;
    }

    public ApplicantStageQualification changeSubmitDate(ApplicantStageQualificationSubmitDate submitDate)
    {
        this.submitDate = submitDate;
        return this;
    }
}
