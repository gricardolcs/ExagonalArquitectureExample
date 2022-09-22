package com.jalasoft.bootcamp.setting.infrastructure.factory;

import com.jalasoft.bootcamp.becommon.domain.ddd.IsFactory;
import com.jalasoft.bootcamp.becommon.domain.exceptions.DuplicatedEntityException;
import com.jalasoft.bootcamp.becommon.infrastructure.factory.Factory;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.Field;
import com.jalasoft.bootcamp.setting.domain.category.Category;
import com.jalasoft.bootcamp.setting.domain.category.CategoryRepository;
import com.jalasoft.bootcamp.setting.infrastructure.dto.category.CategoryDTO;
import java.util.HashSet;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@IsFactory
@Service
@Qualifier("CategoryFactory")
public class CategoryFactory implements Factory<Category, CategoryDTO>
{
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryFactory(final CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(final CategoryDTO context)
    {
        Long categoryId = UUID.randomUUID().getLeastSignificantBits();
        validateIfAlreadyExistsCategory(context);
        return new Category(categoryId, context.getName(), context.getDescription(),
            new HashSet<>(context.getSkills()));
    }

    private void validateIfAlreadyExistsCategory(final CategoryDTO context)
    {
        boolean existAny = categoryRepository.existsByNameIgnoreCase(context.getName());
        if (existAny)
        {
            throw new DuplicatedEntityException(
                Field.NAME,
                ErrorsUtil.getDescription(
                    ErrorsUtil.ERROR_DESCRIPTION_CATEGORY_DUPLICATED,
                    Field.NAME,
                    String.valueOf(context.getName())
                ));
        }
    }
}
