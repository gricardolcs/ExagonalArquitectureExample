package com.jalasoft.bootcamp.setting.infrastructure.usecase.category.manipulation;

import com.jalasoft.bootcamp.becommon.domain.ddd.IsUseCase;
import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.becommon.infrastructure.factory.Factory;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.Field;
import com.jalasoft.bootcamp.setting.domain.category.Category;
import com.jalasoft.bootcamp.setting.domain.category.CategoryRepository;
import com.jalasoft.bootcamp.setting.infrastructure.dto.category.CategoryDTO;
import com.jalasoft.bootcamp.setting.infrastructure.dto.category.CategoryUpdateDto;
import com.jalasoft.bootcamp.setting.infrastructure.dto.skill.SkillRecord;
import com.jalasoft.bootcamp.setting.infrastructure.events.rabbitmq.skills.SkillsEventPublisher;
import com.jalasoft.bootcamp.setting.infrastructure.usecase.category.fetch.CategoryFetchUseCase;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@IsUseCase
@Service
public class CategoryHandleUseCase
{
    private final Factory<Category, CategoryDTO> categoryFactory;
    private final CategoryRepository categoryRepository;
    private final CategoryFetchUseCase categoryFetchUseCase;
    private final SkillsEventPublisher skillsEventPublisher;

    @Autowired
    public CategoryHandleUseCase(
        final Factory<Category, CategoryDTO> categoryFactory,
        final CategoryRepository categoryRepository,
        final CategoryFetchUseCase categoryFetchUseCase,
        final SkillsEventPublisher skillsEventPublisher)
    {
        this.categoryFactory = categoryFactory;
        this.categoryRepository = categoryRepository;
        this.categoryFetchUseCase = categoryFetchUseCase;
        this.skillsEventPublisher = skillsEventPublisher;
    }

    public CategoryDTO create(CategoryDTO categoryDTO)
    {
        final Category category = categoryFactory.create(categoryDTO);
        final Category categorySaved = categoryRepository.save(category);
        publishSkills();
        return new CategoryDTO(categorySaved);
    }

    public void delete(long id, String skillName)
    {
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                Field.ID, ErrorsUtil.getDescription(
                ErrorsUtil.ERROR_DESCRIPTION_CATEGORY_NOT_FOUND,
                Field.ID,
                String.valueOf(id))));
        category.setSkills(new HashSet<>(category.getSkills().stream()
            .filter((current) -> !skillName.equalsIgnoreCase(current)).collect(
                Collectors.toList())));
        categoryRepository.save(category);
        publishSkills();
    }

    public CategoryDTO edit(long categoryId, CategoryUpdateDto categoryUpdateDto)
    {
        Category currentCategory =
            categoryRepository.findById(categoryId).orElseThrow(() -> new EntityNotFoundException(
                Field.ID, ErrorsUtil.getDescription(
                ErrorsUtil.ERROR_DESCRIPTION_CATEGORY_NOT_FOUND,
                Field.ID,
                String.valueOf(categoryId))
            ));

        if (categoryUpdateDto.getName() != currentCategory.getName())
        {
            currentCategory.setName(categoryUpdateDto.getName());
        }

        if (categoryUpdateDto.getRecords().size() > 0)
        {
            List<String> newSkills =
                currentCategory.getSkills().stream().map((current) -> {
                    List<SkillRecord> filteredRecords =
                        categoryUpdateDto.getRecords().stream()
                            .filter(skill -> skill.getOldSkill().equalsIgnoreCase(current)).collect(
                                Collectors.toList());
                    if (filteredRecords.isEmpty())
                    {
                        return current;

                    }
                    return filteredRecords.get(0).getNewSkill();
                }).collect(Collectors.toList());
            currentCategory.setSkills(new HashSet<>(newSkills));
        }
        final Category categorySaved = categoryRepository.save(currentCategory);
        publishSkills();
        return new CategoryDTO(categorySaved);
    }

    private void publishSkills () {
        skillsEventPublisher.handleEvent(categoryFetchUseCase.fetchAllSkills());
    }
}
