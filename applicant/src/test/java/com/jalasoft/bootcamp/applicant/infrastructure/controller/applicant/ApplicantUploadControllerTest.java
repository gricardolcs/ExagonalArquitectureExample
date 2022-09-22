package com.jalasoft.bootcamp.applicant.infrastructure.controller.applicant;

import com.jalasoft.bootcamp.becommon.domain.exceptions.InvalidFileException;
import com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant.ApplicantUploadDTO;
import com.jalasoft.bootcamp.applicant.infrastructure.usecase.applicant.upload.ApplicantUploadUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

class ApplicantUploadControllerTest
{
    @Mock
    private ApplicantUploadUseCase applicantUploadUseCase;

    private ApplicantUploadController applicantUploadController;

    private long bootcampId;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        applicantUploadController = new ApplicantUploadController(applicantUploadUseCase);
        bootcampId = -123456789098L;
    }

    @Test
    public void testUploadAllApplicantsFromCsvSuccessfully() throws IOException
    {
        InputStream inputStream = Mockito.mock(InputStream.class);
        MockMultipartFile mockMultipartFile = Mockito.mock(MockMultipartFile.class);

        Mockito.when(mockMultipartFile.getInputStream()).thenReturn(inputStream);

        ApplicantUploadDTO applicantUploadDTO = new ApplicantUploadDTO(
            List.of("-1122334455", "-99887766"), "", Collections.emptyList());

        Mockito.when(applicantUploadUseCase.uploadApplicants(
                any(InputStreamReader.class), eq(bootcampId)))
            .thenReturn(applicantUploadDTO);

        ResponseEntity<ApplicantUploadDTO> actualResult =
            applicantUploadController.uploadApplicants(mockMultipartFile, bootcampId);

        ApplicantUploadDTO actualBodyResult = actualResult.getBody();

        assertEquals("", Objects.requireNonNull(actualBodyResult).getFailedMessage());
        assertEquals(Collections.emptyList(), actualBodyResult.getFailedRows());
        assertEquals(List.of("-1122334455", "-99887766"), actualBodyResult.getApplicantsIds());
    }

    @Test
    public void testUploadAllApplicantsFromCsvError() throws IOException
    {
        MockMultipartFile mockMultipartFile = Mockito.mock(MockMultipartFile.class);

        Mockito.when(mockMultipartFile.getInputStream()).thenThrow(IOException.class);

        assertThrows(InvalidFileException.class, () -> {
            applicantUploadController.uploadApplicants(mockMultipartFile, bootcampId);
        });
    }
}