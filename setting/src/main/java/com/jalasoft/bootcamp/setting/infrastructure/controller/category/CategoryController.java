package com.jalasoft.bootcamp.setting.infrastructure.controller.category;

import com.jalasoft.bootcamp.setting.infrastructure.dto.category.CategoryUpdateDto;
import com.jalasoft.bootcamp.setting.infrastructure.usecase.category.fetch.CategoryFetchUseCase;
import com.jalasoft.bootcamp.setting.infrastructure.usecase.category.manipulation.CategoryHandleUseCase;
import com.jalasoft.bootcamp.setting.infrastructure.dto.category.CategoryDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan(basePackages="com.jalasoft.bootcamp.becommon.domain.exceptions")
@RestController
@RequestMapping("/category")
public class CategoryController
{
    private final CategoryHandleUseCase categoryHandleUseCase;
    private final CategoryFetchUseCase categoryFetchUseCase;

    @Autowired
    public CategoryController(
        final CategoryHandleUseCase categoryHandleUseCase,
        final CategoryFetchUseCase categoryFetchUseCase)
    {
        this.categoryHandleUseCase = categoryHandleUseCase;
        this.categoryFetchUseCase = categoryFetchUseCase;
    }

    @ApiOperation(
        value = "Creates a new category",
        response = CategoryDTO.class,
        notes = "Creates a new category given the below properties name and category are required"
    )
    @ApiResponses( {
        @ApiResponse(
            code = 201,
            message = "Created, when resource was created successfully and returns the"
                + " category in the body"
        ),
        @ApiResponse(
            code = 409,
            message = "Duplicate name error, when there is already a registered"
                + " Skill with that name sent in the payload"
        ),
        @ApiResponse(
            code = 422,
            message = "Invalid input error, when the payload contains a required"
                + " attribute"
        )
    })
    @PostMapping
    public ResponseEntity<CategoryDTO> create(
        @Valid @RequestBody final CategoryDTO categoryDTO)
    {
        return new ResponseEntity<>(
            categoryHandleUseCase.create(categoryDTO),
            HttpStatus.CREATED);
    }

    @ApiOperation(
        value = "Fetch all categories",
        response = CategoryDTO.class,
        notes = "Fetch all categories"
    )
    @ApiResponses( {
        @ApiResponse(
            code = 200, message = "Fetch all categories successfully"
        )
    })
    @GetMapping
    ResponseEntity<List<CategoryDTO>> fetchAll()
    {
        return new ResponseEntity<>(categoryFetchUseCase.fetchAll(), HttpStatus.OK);
    }

    @ApiOperation(
        value = "Delete category skill by id and skill name"
    )
    @ApiResponses( {
        @ApiResponse(
            code = 204, message = "Delete category skill successfully"
        ),
        @ApiResponse(
            code = 404, message = "Category not found"
        )
    })
    @DeleteMapping("/{id}/skill/{skillName}")
    public ResponseEntity<Void> deleteSkillCategory(@PathVariable("id") long id, @PathVariable(
        "skillName") String skillName) {
        categoryHandleUseCase.delete(id, skillName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @ApiOperation(
        value = "Update category",
        response = CategoryDTO.class
    )
    @ApiResponses( {
        @ApiResponse(
            code = 200, message = "Update category successfully"
        ),
        @ApiResponse(
            code = 404, message = "Category not found"
        )
    })
    @PutMapping("/{id}")
    ResponseEntity<CategoryDTO> updateCategory(
        @PathVariable("id") long id,
        @RequestBody CategoryUpdateDto categoryUpdateDto)
    {
        return new ResponseEntity<>(categoryHandleUseCase.edit(id, categoryUpdateDto), HttpStatus.OK);
    }
}
