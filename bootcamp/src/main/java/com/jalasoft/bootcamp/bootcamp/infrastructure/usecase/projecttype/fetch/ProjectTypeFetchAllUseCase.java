package com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.projecttype.fetch;

import com.jalasoft.bootcamp.becommon.domain.ddd.IsUseCase;
import com.jalasoft.bootcamp.bootcamp.domain.projecttype.ProjectType;
import com.jalasoft.bootcamp.bootcamp.domain.projecttype.ProjectTypeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@IsUseCase
@Service
public class ProjectTypeFetchAllUseCase
{
    private final ProjectTypeRepository projectTypeRepository;

    @Autowired
    public ProjectTypeFetchAllUseCase(final ProjectTypeRepository projectTypeRepository)
    {
        this.projectTypeRepository = projectTypeRepository;
    }

    public List<ProjectType> fetchAll()
    {
        return projectTypeRepository.findAll();
    }
}
