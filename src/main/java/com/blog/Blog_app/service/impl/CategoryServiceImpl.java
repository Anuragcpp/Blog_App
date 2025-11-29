package com.blog.Blog_app.service.impl;

import com.blog.Blog_app.domain.entities.Category;
import com.blog.Blog_app.repository.CategoryRepository;
import com.blog.Blog_app.service.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAllWithPostCount();
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {
        if (categoryRepository.existsByNameIgnoreCase(category.getName())){
            throw new IllegalArgumentException("Category Already Exist with name " + category.getName());
        }
        return categoryRepository.save(category);
    }
}
