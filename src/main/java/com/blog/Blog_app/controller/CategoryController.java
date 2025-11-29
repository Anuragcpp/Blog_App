package com.blog.Blog_app.controller;

import com.blog.Blog_app.domain.dto.category.CategoryDto;
import com.blog.Blog_app.domain.dto.category.CreateCategoryRequest;
import com.blog.Blog_app.domain.dto.respose.SuccessResponse;
import com.blog.Blog_app.domain.entities.Category;
import com.blog.Blog_app.mapper.CategoryMapper;
import com.blog.Blog_app.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<SuccessResponse> listCategory(){
        List<CategoryDto> categories = categoryService.listCategories()
                .stream()
                .map(categoryMapper::toDto)
                .toList();
        return ResponseEntity.ok(
                new SuccessResponse(
                        HttpStatus.OK.value(),
                        "Category list Fatched successfully",
                        categories
                )
        );
    }

    @PostMapping
    public ResponseEntity<SuccessResponse> createCategory(
            @Valid @RequestBody CreateCategoryRequest createCategoryRequest
    ) {

        Category categoryToSave = categoryMapper.toEntity(createCategoryRequest);
        Category savedCategory = categoryService.createCategory(categoryToSave);
        return new ResponseEntity<>(
                new SuccessResponse(
                        HttpStatus.CREATED.value(),
                        "Category Added Successfully",
                        savedCategory
                ),
                HttpStatus.CREATED
        );

    }

}
