package com.jalasoft.bootcamp.bootcamp.infrastructure.controller.projecttype;

import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.projecttype.ProjectTypeDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.projecttype.fetch.ProjectTypeFetchAllUseCase;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan(basePackages="com.jalasoft.bootcamp.becommon.domain.exceptions")
@RestController
public class ProjectTypeFetchAllController
{
    private final ProjectTypeFetchAllUseCase projectTypeFetchAllUseCase;

    @Autowired
    public ProjectTypeFetchAllController(
        final ProjectTypeFetchAllUseCase projectTypeFetchAllUseCase)
    {
        this.projectTypeFetchAllUseCase = projectTypeFetchAllUseCase;
    }

    @GetMapping("/projectType")
    public ResponseEntity<List<ProjectTypeDTO>> fetchAllProjectTypes()
    {
        return ResponseEntity.ok(
            projectTypeFetchAllUseCase.fetchAll().stream()
                .map(ProjectTypeDTO::new)
                .collect(Collectors.toList()));
    }
}
