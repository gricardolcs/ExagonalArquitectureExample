package com.jalasoft.bootcamp.bootcamp.infrastructure.controller.status;

import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.status.StatusDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.status.fetch.StatusFetchAllUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@ComponentScan(basePackages="com.jalasoft.bootcamp.becommon.domain.exceptions")
@RestController
public class StatusFetchAllController
{
    private final StatusFetchAllUseCase statusFetchAllUseCase;

    @Autowired
    public StatusFetchAllController(final StatusFetchAllUseCase statusFetchAllUseCase)
    {
        this.statusFetchAllUseCase = statusFetchAllUseCase;
    }

    @GetMapping("/statuses")
    public ResponseEntity<List<StatusDTO>> fetchAllStatuses()
    {
        return ResponseEntity.ok(statusFetchAllUseCase.fetchAll().stream()
            .map(StatusDTO::new)
            .collect(Collectors.toList()));
    }
}
