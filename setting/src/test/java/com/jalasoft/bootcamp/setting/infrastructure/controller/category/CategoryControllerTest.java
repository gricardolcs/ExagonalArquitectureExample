package com.jalasoft.bootcamp.setting.infrastructure.controller.category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.jalasoft.bootcamp.becommon.domain.exceptions.DuplicatedEntityException;
import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.Field;
import com.jalasoft.bootcamp.setting.domain.category.Category;
import com.jalasoft.bootcamp.setting.infrastructure.dto.category.CategoryUpdateDto;
import com.jalasoft.bootcamp.setting.infrastructure.usecase.category.fetch.CategoryFetchUseCase;
import com.jalasoft.bootcamp.setting.infrastructure.usecase.category.manipulation.CategoryHandleUseCase;
import com.jalasoft.bootcamp.setting.infrastructure.dto.category.CategoryDTO;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest
{
    @Mock
    private CategoryHandleUseCase categoryHandleUseCase;
    @Mock
    private CategoryFetchUseCase categoryFetchUseCase;
    @InjectMocks
    private CategoryController categoryController;
    private Long categoryId;
    private String name;
    private String description;
    private List<String> skills;

    @BeforeEach
    public void setUp()
    {
        categoryId = -1L;
        name = "Programing Language";
        description = "Category description ";
        skills = List.of("C#", "Java");
    }

    @Test
    void testCreateValidCategory()
    {
        CategoryDTO categoryDTO = new CategoryDTO(name, description, skills);
        List<String> addedSkills = List.of("C#");
        List<String> existingSkills = List.of("Java");

        when(categoryHandleUseCase.create(categoryDTO))
            .thenReturn(categoryDTO);

        ResponseEntity<CategoryDTO> response = categoryController.create(categoryDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(name, response.getBody().getName());
        assertEquals(skills, response.getBody().getSkills());
    }

    @Test
    void testCreateInvalidCategory()
    {

        CategoryDTO categoryDTO = new CategoryDTO("", description, skills);
        when(categoryHandleUseCase.create(categoryDTO))
            .thenThrow(new DuplicatedEntityException(
                Field.NAME,
                ErrorsUtil.getDescription(
                    ErrorsUtil.ERROR_DESCRIPTION_CATEGORY_DUPLICATED,
                    Field.NAME,
                    ""
                )));
        Assertions.assertThrows(
            DuplicatedEntityException.class,
            () -> categoryController.create(categoryDTO));
    }

    @Test
    void testFetchAllCategories()
    {
        Category category = new Category(categoryId, name, description, new HashSet<>(skills));
        CategoryDTO categoryDTO = new CategoryDTO(category);
        Mockito.when(categoryFetchUseCase.fetchAll()).thenReturn(Arrays.asList(categoryDTO));

        ResponseEntity<List<CategoryDTO>> response = categoryController.fetchAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categoryDTO, response.getBody().get(0));
    }

    @Test
    public void deleteUserById()
    {
        doNothing().when(categoryHandleUseCase).delete(any(Long.class), any(String.class));
        ResponseEntity<Void> response = categoryController.deleteSkillCategory(-12345, "test");
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void deleteUserByIdWithInvalidId()
    {
        doThrow(EntityNotFoundException.class).when(categoryHandleUseCase).delete(any(Long.class)
            , any(String.class));
        Assertions.assertThrows(
            EntityNotFoundException.class,
            () -> categoryController.deleteSkillCategory(-12345, "test"));
    }

    @Test
    public void testEditCategory()
    {
        CategoryUpdateDto updateData = new CategoryUpdateDto();
        CategoryDTO testData = new CategoryDTO("category edit name", description, skills);
        when(categoryHandleUseCase.edit(any(long.class), any(CategoryUpdateDto.class))).thenReturn(
            testData);
        ResponseEntity<CategoryDTO> response = categoryController.updateCategory(-123456,
            updateData);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testData, response.getBody());
    }

    @Test
    void testInvalidEditCategory()
    {
        CategoryDTO testData = new CategoryDTO("category edit name", description, skills);
        when(categoryHandleUseCase.edit(any(long.class), any(CategoryUpdateDto.class))).thenThrow(
            EntityNotFoundException.class);
        Assertions.assertThrows(
            EntityNotFoundException.class,
            () -> categoryController.updateCategory(-12345, new CategoryUpdateDto()));
    }
}