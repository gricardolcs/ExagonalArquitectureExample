package com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.workflow.fetch;

import com.jalasoft.bootcamp.becommon.domain.ddd.IsUseCase;
import com.jalasoft.bootcamp.bootcamp.domain.workflow.Workflow;
import com.jalasoft.bootcamp.bootcamp.domain.workflow.WorkflowRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@IsUseCase
@Service
public class WorkflowFetchAllUseCase
{
    private final WorkflowRepository workflowRepository;

    @Autowired
    public WorkflowFetchAllUseCase(final WorkflowRepository workflowRepository)
    {
        this.workflowRepository = workflowRepository;
    }

    public List<Workflow> fetchAll()
    {
        return workflowRepository.findAll();
    }
}
