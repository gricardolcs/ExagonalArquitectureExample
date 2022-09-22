package com.jalasoft.bootcamp.setting.infrastructure.usecase.category.fetch;

import com.jalasoft.bootcamp.becommon.domain.ddd.IsUseCase;
import com.jalasoft.bootcamp.setting.domain.category.CategoryRepository;
import com.jalasoft.bootcamp.setting.infrastructure.dto.category.CategoryDTO;
import com.jalasoft.bootcamp.setting.infrastructure.utils.category.CategoryDTOBuilder;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@IsUseCase
@Service
public class CategoryFetchUseCase
{
    private final CategoryRepository categoryRepository;
    private final CategoryDTOBuilder categoryDTOBuilder;

    @Autowired
    public CategoryFetchUseCase(
        final CategoryRepository categoryRepository,
        final CategoryDTOBuilder categoryDTOBuilder)
    {
        this.categoryRepository = categoryRepository;
        this.categoryDTOBuilder = categoryDTOBuilder;
    }

    public List<CategoryDTO> fetchAll()
    {
        return categoryRepository.findAll().stream().map(categoryDTOBuilder::build)
            .collect(Collectors.toList());
    }

    public List<String> fetchAllSkills()
    {
        return fetchAll().stream().map(CategoryDTO::getSkills).flatMap(List::stream)
            .collect(Collectors.toList());
    }
}
