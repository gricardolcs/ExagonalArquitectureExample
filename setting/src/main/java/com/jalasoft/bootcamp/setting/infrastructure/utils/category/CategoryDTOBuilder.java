package com.jalasoft.bootcamp.setting.infrastructure.utils.category;

import com.jalasoft.bootcamp.setting.domain.category.Category;
import com.jalasoft.bootcamp.setting.infrastructure.dto.category.CategoryDTO;
import org.springframework.stereotype.Service;

@Service
public class CategoryDTOBuilder
{
    public CategoryDTO build(Category category)
    {
        return new CategoryDTO(category);
    }
}
