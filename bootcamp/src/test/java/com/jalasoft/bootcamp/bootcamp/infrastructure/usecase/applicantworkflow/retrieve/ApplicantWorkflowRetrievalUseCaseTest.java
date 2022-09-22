package com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.applicantworkflow.retrieve;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jalasoft.bootcamp.bootcamp.domain.applicantworkflow.ApplicantWorkflow;
import com.jalasoft.bootcamp.bootcamp.domain.applicantworkflow.ApplicantWorkflowRepository;
import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.Bootcamp;
import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.BootcampRepository;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class ApplicantWorkflowRetrievalUseCaseTest
{

    @Mock
    private ApplicantWorkflowRepository applicantWorkflowRepository;

    @Mock
    private BootcampRepository bootcampRepository;

    private ApplicantWorkflowRetrievalUseCase applicantWorkflowRetrievalUseCase;

    private long bootcampId;

    private long applicantId;

    private Bootcamp bootcamp;

    private ApplicantWorkflow applicantWorkflow;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        bootcampId = 1L;
        applicantId = 1L;
        bootcamp = new Bootcamp(bootcampId,
            "Dev bootcamp"
            , "First Bolivian Bootcamp"
            , "Av. Melchor Perez #123"
            , LocalDate.of(2021, 7, 7)
            , LocalDate.of(2021, 12, 12)
            , 1, 1L, 1, 1L,
            30, new HashSet<>()
            , 1, 1L, Collections.emptySet());

        applicantWorkflow = new ApplicantWorkflow(UUID.randomUUID().getLeastSignificantBits()
            , bootcampId, applicantId, Collections.emptySet());

        this.applicantWorkflowRetrievalUseCase = new ApplicantWorkflowRetrievalUseCase(
            applicantWorkflowRepository, bootcampRepository);
    }

    @Test
    public void testRetrieveApplicantWorkflowWhenBootcampAndApplicantExist()
    {
        Mockito.when(bootcampRepository.findById(bootcampId))
            .thenReturn(Optional.of(bootcamp));

        Mockito.when(applicantWorkflowRepository
                .findByApplicantIdAndBootcampId(applicantId, bootcampId))
            .thenReturn(Optional.of(applicantWorkflow));

        ApplicantWorkflow actualResult = applicantWorkflowRetrievalUseCase
            .retrieveApplicantWorkflowByBootcampIdAndApplicantId(bootcampId, applicantId);

        assertEquals(1L, actualResult.getBootcampId());
        assertEquals(1L, actualResult.getApplicantId());
        assertEquals(0, actualResult.getApplicantStageQualificationList().size());
    }

    @Test
    public void testRetrieveApplicantWorkflowExceptionWhenBootcampDoesNotExist()
    {
        Mockito.when(bootcampRepository.findById(bootcampId))
            .thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () ->
            applicantWorkflowRetrievalUseCase
                .retrieveApplicantWorkflowByBootcampIdAndApplicantId(bootcampId, applicantId));

        assertSame(ErrorsUtil.ERROR_MESSAGE_ENTITY_NOT_FOUND, exception.getErrorMessage());
    }

    @Test
    public void testRetrieveApplicantWorkflowExceptionWhenApplicantWorkflowDoesNotExist()
    {
        Mockito.when(bootcampRepository.findById(bootcampId))
            .thenReturn(Optional.of(bootcamp));

        Mockito.when(applicantWorkflowRepository.findByApplicantIdAndBootcampId(applicantId
            , bootcampId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
            EntityNotFoundException.class, () -> applicantWorkflowRetrievalUseCase
                .retrieveApplicantWorkflowByBootcampIdAndApplicantId(bootcampId, applicantId));

        assertSame(ErrorsUtil.ERROR_MESSAGE_ENTITY_NOT_FOUND, exception.getErrorMessage());
    }
}