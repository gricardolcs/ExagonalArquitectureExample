package com.jalasoft.bootcamp.bootcamp.domain.bootcamp;

import com.jalasoft.bootcamp.becommon.domain.ddd.IsEntity;

import java.time.LocalDate;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.jalasoft.bootcamp.becommon.domain.exceptions.InvalidArgumentsEntityException;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.Field;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;

@Getter
@IsEntity
@Entity
@Table(name = "Bootcamp")
public class Bootcamp extends AbstractAggregateRoot<Bootcamp>
{
    @Id
    private Long id;

    private String name;

    private String description;

    private String location;

    @Column(name = "start_date")
    @AttributeOverrides( {
        @AttributeOverride(name = "date", column = @Column(name = "startDate", nullable = false))
    })
    private LocalDate bootcampStartDate;

    @Column(name = "end_date")
    @AttributeOverrides( {
        @AttributeOverride(name = "date", column = @Column(name = "endDate", nullable = false))
    })
    private LocalDate bootcampEndDate;

    // This will be removed when we will create the use case to associate bootcamp to a workflow
    @Column
    private int workflowType;

    @Column(name = "workflowId")
    @AttributeOverride(name = "id", column = @Column(name = "workflowId", nullable = false))
    private long workflowId;

    @Column
    private int department;

    @Column(name = "departmentId")
    @AttributeOverride(name = "id", column = @Column(name = "departmentId", nullable = false))
    private long departmentId;

    @Column(name = "participants_expected_quantity")
    private Integer bootcampParticipantsExpectedQuantity;

    @Column
    @ElementCollection
    @CollectionTable(name = "AcceptedApplicant", joinColumns = @JoinColumn(name = "bootcampId"))
    private Set<Long> acceptedParticipants;

    @Column
    private int projectType;

    @Column(name = "projectTypeId")
    @AttributeOverride(name = "id", column = @Column(name = "projectTypeId", nullable = false))
    private long projectTypeId;

    @ElementCollection
    @Column(name = "applicantId")
    @CollectionTable(name = "BootcampApplicant", joinColumns = @JoinColumn(name = "bootcampId"))
    private Set<Long> bootcampMembers;

    /*
     * Used only by JPA.
     */
    protected Bootcamp()
    {
        // JPA usage only.
    }

    public Bootcamp(
        final Long id, final String bootcampName,
        final String bootcampDescription, final String bootcampLocation,
        final LocalDate bootcampStartDate, final LocalDate bootcampEndDate,
        final int workflowType, final Long workflowId, final int department, final Long departmentId,
        final Integer bootcampParticipantsExpectedQuantity,
        final Set<Long> acceptedParticipants, final int projectType, final Long projectTypeId,
        final Set<Long> bootcampMembers)
    {
        validateStartDateIsBeforeEndDate(bootcampStartDate, bootcampEndDate);

        this.id = id;
        this.name = bootcampName;
        this.description = bootcampDescription;
        this.location = bootcampLocation;
        this.bootcampStartDate = bootcampStartDate;
        this.bootcampEndDate = bootcampEndDate;
        this.workflowType = workflowType;
        this.workflowId = workflowId;
        this.department = department;
        this.departmentId = departmentId;
        this.bootcampParticipantsExpectedQuantity = bootcampParticipantsExpectedQuantity;
        this.acceptedParticipants = acceptedParticipants;
        this.projectType = projectType;
        this.projectTypeId = projectTypeId;
        this.bootcampMembers = bootcampMembers;
    }

    private void validateStartDateIsBeforeEndDate(
        final LocalDate bootcampStartDate, final LocalDate bootcampEndDate)
    {
        if (bootcampStartDate.isAfter(bootcampEndDate))
        {
            throw new InvalidArgumentsEntityException(
                Field.DATE,
                ErrorsUtil.ERROR_DESCRIPTION_INVALID_START_DATE);
        }
    }

    public void changeName(String bootcampName)
    {
        this.name = bootcampName;
    }

    public void changeDescription(String bootcampDescription)
    {
        this.description = bootcampDescription;
    }

    public void changeStartDate(LocalDate bootcampStartDate)
    {
        this.bootcampStartDate = bootcampStartDate;
    }

    public void changeEndDate(LocalDate bootcampEndDate)
    {
        this.bootcampEndDate = bootcampEndDate;
    }

    public void changeWorkflowType(int workflowType)
    {
        this.workflowType = workflowType;
        this.workflowId = workflowType;
    }

    public void changeDepartment(int department)
    {
        this.department = department;
        this.departmentId = department;
    }

    public void changeBootcampParticipantsExpectedQuantity(
        Integer bootcampParticipantsExpectedQuantity)
    {
        this.bootcampParticipantsExpectedQuantity = bootcampParticipantsExpectedQuantity;
    }

    public void changeProjectType(int projectType)
    {
        this.projectType = projectType;
        this.projectTypeId = projectType;
    }

    public void addMembers(final Set<Long> bootcampMembers)
    {
        this.bootcampMembers.addAll(bootcampMembers);
    }

    public void removeAcceptedParticipants(Long applicantId)
    {
        acceptedParticipants.remove(applicantId);
    }

    public void addAcceptedParticipant(Long applicantId)
    {
        acceptedParticipants.add(applicantId);
    }
}
