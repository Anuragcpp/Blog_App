package com.blog.Blog_app.controller;

import com.blog.Blog_app.domain.dto.CategoryDto;
import com.blog.Blog_app.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    public ResponseEntity<List<CategoryDto>> listCategory(){

    }

}
