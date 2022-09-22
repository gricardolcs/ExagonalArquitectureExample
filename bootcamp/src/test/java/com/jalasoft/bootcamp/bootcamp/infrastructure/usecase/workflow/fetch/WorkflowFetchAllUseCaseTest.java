package com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.workflow.fetch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jalasoft.bootcamp.bootcamp.domain.stage.Stage;
import com.jalasoft.bootcamp.bootcamp.domain.stage.StageName;
import com.jalasoft.bootcamp.bootcamp.domain.stage.StageOrder;
import com.jalasoft.bootcamp.bootcamp.domain.workflow.Workflow;
import com.jalasoft.bootcamp.bootcamp.domain.workflow.WorkflowName;
import com.jalasoft.bootcamp.bootcamp.domain.workflow.WorkflowRepository;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class WorkflowFetchAllUseCaseTest {

    @Mock
    private WorkflowRepository workflowRepository;

    private WorkflowFetchAllUseCase workflowFetchAllUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        workflowFetchAllUseCase = new WorkflowFetchAllUseCase(workflowRepository);
    }

    @Test
    public void testGetAllWorkflowsSuccessfully() {
        List<Workflow> workflows = List.of(
            new Workflow(1L, new WorkflowName("Development bootcamp"), Collections.emptyList()),
            new Workflow(2L, new WorkflowName("Manual testing bootcamp")
                , Collections.emptyList()));

        Mockito.when(workflowRepository.findAll()).thenReturn(workflows);
        List<Workflow> actualWorkflows = workflowFetchAllUseCase.fetchAll();

        assertEquals(2, actualWorkflows.size());
    }

    @Test
    public void testGetWorkflowsWithStagesOrderedCorrectly() {
        List<Stage> stages = List.of(
            new Stage(1L, new StageName("Initial"), new StageOrder(1)
                , false, 1L, null, false, false),
            new Stage(2L, new StageName("Psychometric"), new StageOrder(1)
                , false, 1L, null, false, false));

        List<Workflow> workflows = List.of(
            new Workflow(1L, new WorkflowName("Development bootcamp"), stages)
        );

        Mockito.when(workflowRepository.findAll()).thenReturn(workflows);
        List<Workflow> actualWorkflows = workflowFetchAllUseCase.fetchAll();

        assertEquals(1, actualWorkflows.size());

        assertEquals("Initial",
            actualWorkflows.get(0).getStages().get(0).getStageName().getName());

        assertEquals("Psychometric",
            actualWorkflows.get(0).getStages().get(1).getStageName().getName());
    }

    @Test
    public void testGetAllWorkflowsEmptyResult() {
        List<Workflow> workflows = Collections.emptyList();
        Mockito.when(workflowRepository.findAll()).thenReturn(workflows);
        List<Workflow> actualWorkflows = workflowFetchAllUseCase.fetchAll();

        assertEquals(0, actualWorkflows.size());
    }
}
