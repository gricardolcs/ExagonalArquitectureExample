package com.jalasoft.bootcamp.bootcamp.domain.department;

import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.Bootcamp;
import com.jalasoft.bootcamp.becommon.domain.ddd.IsEntity;
import com.jalasoft.bootcamp.bootcamp.domain.workflow.Workflow;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

@IsEntity
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Department")
public class Department extends AbstractAggregateRoot<Department>
{
    @Id
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @OneToMany
    @JoinColumn(name = "departmentId", referencedColumnName = "id")
    private List<Bootcamp> bootcampList;


    public Department(Long id, String name, String description)
    {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
