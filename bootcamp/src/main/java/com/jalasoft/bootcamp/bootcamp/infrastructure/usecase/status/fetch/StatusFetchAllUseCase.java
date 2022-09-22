package com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.status.fetch;

import com.jalasoft.bootcamp.becommon.domain.ddd.IsUseCase;
import com.jalasoft.bootcamp.bootcamp.domain.status.Status;
import com.jalasoft.bootcamp.bootcamp.domain.status.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@IsUseCase
@Service
public class StatusFetchAllUseCase
{
    private final StatusRepository statusRepository;

    @Autowired
    public StatusFetchAllUseCase(final StatusRepository statusRepository)
    {
        this.statusRepository = statusRepository;
    }

    public List<Status> fetchAll()
    {
        return statusRepository.findAll();
    }
}
