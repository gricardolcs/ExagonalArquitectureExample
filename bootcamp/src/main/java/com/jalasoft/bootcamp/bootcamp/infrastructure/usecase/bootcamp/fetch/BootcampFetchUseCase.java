package com.jalasoft.bootcamp.bootcamp.infrastructure.usecase.bootcamp.fetch;

import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.Bootcamp;
import com.jalasoft.bootcamp.bootcamp.domain.bootcamp.BootcampRepository;
import com.jalasoft.bootcamp.becommon.domain.ddd.IsUseCase;
import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.bootcamp.infrastructure.dto.bootcamp.BootcampDTO;
import com.jalasoft.bootcamp.bootcamp.infrastructure.utils.BootcampDTOBuilder;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.Field;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@IsUseCase
@Service
public class BootcampFetchUseCase
{
    private final BootcampRepository bootcampRepository;

    private final BootcampDTOBuilder bootcampDTOBuilder;

    @Autowired
    public BootcampFetchUseCase(
        BootcampRepository bootcampRepository,
        BootcampDTOBuilder bootcampDTOBuilder)
    {
        this.bootcampRepository = bootcampRepository;
        this.bootcampDTOBuilder = bootcampDTOBuilder;
    }

    public BootcampDTO findById(final long id)
    {
        Bootcamp bootcamp = bootcampRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                Field.ID,
                ErrorsUtil.getDescription(
                    ErrorsUtil.ERROR_DESCRIPTION_BOOTCAMP_NOT_FOUND,
                    Field.ID,
                    String.valueOf(id))
            ));

        return bootcampDTOBuilder.build(bootcamp);
    }

    public List<BootcampDTO> fetchAll()
    {
        List<Bootcamp> bootcamps = bootcampRepository.findAll();
        return bootcamps.stream()
            .map(bootcampDTOBuilder::build)
            .collect(Collectors.toList());
    }

    public Page<BootcampDTO> fetch(Pageable pageable)
    {
        Page<Bootcamp> bootcampPage = bootcampRepository.findAll(pageable);
        return new PageImpl<>(bootcampPage.getContent()
            .stream()
            .map(bootcampDTOBuilder::build)
            .collect(Collectors.toList()), pageable, bootcampPage.getTotalElements());
    }

    public List<BootcampDTO> findBootcampByName(final String name)
    {
        List<Bootcamp> bootcamps = bootcampRepository.findByName(name);
        return bootcamps.stream()
            .map(bootcampDTOBuilder::build)
            .collect(Collectors.toList());
    }

    public List<BootcampDTO> findByCriteria(final String criteria)
    {
        List<Bootcamp> bootcamps = bootcampRepository.findBootcampByCriteria(criteria);
        return bootcamps.stream()
            .map(bootcampDTOBuilder::build)
            .collect(Collectors.toList());
    }

    public List<BootcampDTO> getAllBootcampsExcludingApplicant(
        final Long applicantId,
        final String nameCriteria,
        final Sort sort)
    {
        List<Bootcamp> bootcampList = !nameCriteria.isEmpty() ?
            bootcampRepository.findByNameIgnoreCaseContainsAndBootcampMembersNotContains(
                nameCriteria,
                applicantId, sort) :
            bootcampRepository.findBootcampsByBootcampMembersNotContains(applicantId, sort);
        return bootcampList.stream()
            .map(bootcampDTOBuilder::build)
            .collect(Collectors.toList());
    }
}
