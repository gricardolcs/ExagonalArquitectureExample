package com.jalasoft.bootcamp.bootcamp.infrastructure.controller.bootcamp;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.Bootcamp;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.bootcamp.BootcampDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.bootcamp.manipulation.BootcampHandleUseCase;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class BootcampEditionControllerTest
{
    @Mock
    private BootcampHandleUseCase bootcampHandleUseCase;

    @InjectMocks
    private BootcampController bootcampController;

    @Test
    public void testEditAValidBootcamp()
    {
        long bootcampId = UUID.randomUUID().getLeastSignificantBits();
        String name = "Tech Elevator V2";
        String description = "Tech Elevator offers a coding bootcamp dedicated to readying "
            + "students.";
        String location = "Av. Melchor Perez #123";
        LocalDate startDate = LocalDate.of(2021, 3, 15);
        LocalDate endDate = LocalDate.of(2021, 6, 15);
        Bootcamp bootcampEdited = new Bootcamp(bootcampId, name,
            description, location,
            startDate, endDate, 1, 1L, 1, 1L,
            30, new HashSet<>(), 1, 1L,
            Collections.emptySet());
        BootcampDTO bootcampEditionDTO = new BootcampDTO(bootcampEdited);

        when(bootcampHandleUseCase.edit(bootcampId, bootcampEditionDTO))
            .thenReturn(new BootcampDTO(bootcampEdited));
        ResponseEntity<BootcampDTO> response = bootcampController.edit(
            bootcampId,
            bootcampEditionDTO);

        assertSame(HttpStatus.OK, response.getStatusCode());
    }
}