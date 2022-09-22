package com.jalasoft.bootcamp.setting.infrastructure.usecase.category.fetch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.jalasoft.bootcamp.setting.domain.category.Category;
import com.jalasoft.bootcamp.setting.domain.category.CategoryRepository;
import com.jalasoft.bootcamp.setting.infrastructure.dto.category.CategoryDTO;
import com.jalasoft.bootcamp.setting.infrastructure.utils.category.CategoryDTOBuilder;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CategoryFetchUseCaseTest
{
    @Mock
    private CategoryDTOBuilder categoryDTOBuilder;
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryFetchUseCase categoryFetchUseCase;
    private long categoryId;
    private String categoryName;
    private String description;
    private List<String> skills;

    @BeforeEach
    void setUp()
    {
        categoryId = -1L;
        categoryName = "Programing Language";
        description = "Category description";
        skills = List.of("java", "C#");
    }

    @Test
    public void testFetchAllCategories()
    {
        Category category = new Category(categoryId,
            categoryName, description, new HashSet<>(skills));
        when(categoryRepository.findAll()).thenReturn(List.of(category));
        when(categoryDTOBuilder.build(any(Category.class))).thenReturn(new CategoryDTO(category));
        List<CategoryDTO> categoryDTOS = categoryFetchUseCase.fetchAll();
        assertEquals(categoryId, categoryDTOS.get(0).getId());
        assertEquals(categoryName, categoryDTOS.get(0).getName());
        assertEquals(
            List.of("C#", "java"),
            categoryDTOS.get(0).getSkills());
    }
}