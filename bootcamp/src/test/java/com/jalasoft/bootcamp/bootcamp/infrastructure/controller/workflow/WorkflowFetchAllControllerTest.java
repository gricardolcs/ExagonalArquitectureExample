package com.jalasoft.bootcamp.bootcamp.infrastructure.controller.workflow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jalasoft.bootcamp.bootcamp.domain.workflow.Workflow;
import com.jalasoft.bootcamp.bootcamp.domain.workflow.WorkflowName;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.workflow.WorkflowStagesDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.workflow.fetch.WorkflowFetchAllUseCase;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class WorkflowFetchAllControllerTest {

    @Mock
    private WorkflowFetchAllUseCase workflowFetchAllUseCase;

    private WorkflowFetchAllController workflowFetchAllController;

    private List<Workflow> workflows;

    @BeforeEach
    public void setUp() {
        workflows = List.of(
            new Workflow(1L, new WorkflowName("Development bootcamp"), Collections.emptyList()),
            new Workflow(2L, new WorkflowName("Manual testing bootcamp"), Collections.emptyList()));

        workflowFetchAllController = new WorkflowFetchAllController(
            workflowFetchAllUseCase);
    }

    @Test
    public void testFetchAllWorkflowsSuccessfully() {
        Mockito.when(workflowFetchAllUseCase.fetchAll()).thenReturn(workflows);

        ResponseEntity<List<WorkflowStagesDTO>> responseEntity =
            workflowFetchAllController.fetchAllWorkflows();

        List<WorkflowStagesDTO> workflowStagesDTOS = responseEntity.getBody();

        assertEquals(2, workflowStagesDTOS.size());
        assertEquals("Development bootcamp", workflowStagesDTOS.get(0).getName());
        assertEquals("Manual testing bootcamp", workflowStagesDTOS.get(1).getName());
    }
}
