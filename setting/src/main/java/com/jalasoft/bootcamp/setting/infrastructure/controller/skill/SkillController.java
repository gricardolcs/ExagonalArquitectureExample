package com.jalasoft.bootcamp.setting.infrastructure.controller.skill;

import com.jalasoft.bootcamp.setting.infrastructure.usecase.category.fetch.CategoryFetchUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan(basePackages="com.jalasoft.bootcamp.becommon.domain.exceptions")
@RestController
@RequestMapping("/skills")
public class SkillController
{
    private final CategoryFetchUseCase categoryFetchUseCase;

    @Autowired
    public SkillController(
        final CategoryFetchUseCase categoryFetchUseCase)
    {
        this.categoryFetchUseCase = categoryFetchUseCase;
    }

    @ApiOperation(
        value = "Fetch all skills",
        response = List.class,
        notes = "Fetch all skills"
    )
    @ApiResponses( {
        @ApiResponse(
            code = 200, message = "Fetch all skills successfully"
        )
    })
    @GetMapping
    ResponseEntity<List<String>> fetchAll()
    {
        return new ResponseEntity<>(categoryFetchUseCase.fetchAllSkills(), HttpStatus.OK);
    }
}
