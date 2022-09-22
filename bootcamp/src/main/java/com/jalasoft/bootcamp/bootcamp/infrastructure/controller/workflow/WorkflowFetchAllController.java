package com.jalasoft.bootcamp.bootcamp.infrastructure.controller.workflow;

import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.workflow.WorkflowStagesDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.workflow.fetch.WorkflowFetchAllUseCase;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan(basePackages="com.jalasoft.bootcamp.becommon.domain.exceptions")
@RestController
public class WorkflowFetchAllController
{
    private final WorkflowFetchAllUseCase workflowFetchAllUseCase;

    @Autowired
    public WorkflowFetchAllController(final WorkflowFetchAllUseCase workflowFetchAllUseCase)
    {
        this.workflowFetchAllUseCase = workflowFetchAllUseCase;
    }

    @GetMapping("/workflow")
    public ResponseEntity<List<WorkflowStagesDTO>> fetchAllWorkflows()
    {
        return ResponseEntity.ok(
            workflowFetchAllUseCase.fetchAll().stream()
                .map(WorkflowStagesDTO::new)
                .collect(Collectors.toList()));
    }
}
