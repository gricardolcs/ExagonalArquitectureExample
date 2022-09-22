package com.jalasoft.bootcamp.setting.infrastructure.usecase.category.manipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.jalasoft.bootcamp.becommon.domain.exceptions.DuplicatedEntityException;
import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.becommon.infrastructure.factory.Factory;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.Field;
import com.jalasoft.bootcamp.setting.domain.category.Category;
import com.jalasoft.bootcamp.setting.domain.category.CategoryRepository;;
import com.jalasoft.bootcamp.setting.infrastructure.dto.category.CategoryDTO;
import com.jalasoft.bootcamp.setting.infrastructure.dto.category.CategoryUpdateDto;
import com.jalasoft.bootcamp.setting.infrastructure.events.rabbitmq.skills.SkillsEventPublisher;
import com.jalasoft.bootcamp.setting.infrastructure.usecase.category.fetch.CategoryFetchUseCase;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CategoryHandleUseCaseTest
{
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private Factory<Category, CategoryDTO> categoryFactory;
    @Mock
    private SkillsEventPublisher skillsEventPublisher;
    @Mock
    private CategoryFetchUseCase categoryFetchUseCase;

    @InjectMocks
    private CategoryHandleUseCase categoryHandleUseCase;

    private long categoryId;
    private String categoryName;
    private String description;
    private String skillName;

    @BeforeEach
    void setUp()
    {
        categoryId = -1L;
        categoryName = "Programing Language";
        description = "Category description";
        skillName = "C#";
    }

    @Test
    public void testCreateValidCategory()
    {
        ArrayList<String> skills = new ArrayList<>();
        skills.add(skillName);
        CategoryDTO categoryDTO = new CategoryDTO(categoryName, description, skills);
        Category category = new Category(categoryId, categoryName, description,
            new HashSet<>(skills));
        when(categoryFactory.create(categoryDTO)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(category);
        when(categoryFetchUseCase.fetchAllSkills()).thenReturn(skills);

        CategoryDTO categoryCreationDTO = categoryHandleUseCase.create(categoryDTO);
        assertFalse(categoryCreationDTO.getSkills().isEmpty());
        assertTrue(categoryCreationDTO.getSkills().size() == 1);
        assertEquals(List.of(skillName), categoryCreationDTO.getSkills());
    }

    @Test
    public void testCreateInvalidCategory()
    {
        ArrayList<String> skills = new ArrayList<>();
        skills.add(skillName);
        CategoryDTO categoryDTO = new CategoryDTO("", description, skills);
        when(categoryFactory.create(categoryDTO)).thenThrow(new DuplicatedEntityException(
            Field.NAME,
            ErrorsUtil.getDescription(
                ErrorsUtil.ERROR_DESCRIPTION_CATEGORY_DUPLICATED,
                Field.NAME,
                ""
            )));

        Assertions.assertThrows(
            DuplicatedEntityException.class,
            () -> categoryHandleUseCase.create(categoryDTO));
    }

    @Test
    public void testDeleteCategory()
    {
        CategoryHandleUseCase mock = mock(CategoryHandleUseCase.class);
        long id = UUID.randomUUID().getLeastSignificantBits();
        mock.delete(id, "test");
        verify(mock, times(1)).delete(id, "test");
    }

    @Test
    public void testDeleteCategoryInvalidId()
    {
        when(categoryRepository.findById(any(Long.class))).thenThrow(EntityNotFoundException.class);
        Assertions.assertThrows(
            EntityNotFoundException.class,
            () -> categoryHandleUseCase.delete(-12345, "test"));
    }
    
    public void testEditValidCategory()
    {
        long id = -123456;
        ArrayList<String> skills = new ArrayList<>();
        skills.add(skillName);
        Category databaseCategory = new Category(id, "Test edit category", description, null);
        when(categoryRepository.findById(any(Long.class))).thenReturn(Optional.of(databaseCategory));
        when(categoryRepository.save(any(Category.class))).thenReturn(
            new Category(id, "test edit successfully", description, new HashSet<>(skills)));
        CategoryDTO result = categoryHandleUseCase.edit(id, new CategoryUpdateDto());
        assertEquals(id, result.getId());
        assertEquals("test edit successfully", result.getName());
        assertNotNull(result.getSkills());
        assertEquals(1, result.getSkills().size());
    }

    @Test
    public void testEditInvalidCategory()
    {
        when(categoryRepository.findById(any(Long.class))).thenThrow(EntityNotFoundException.class);
        Assertions.assertThrows(
            EntityNotFoundException.class,
            () -> categoryHandleUseCase.edit(-12345, null));
    }
}