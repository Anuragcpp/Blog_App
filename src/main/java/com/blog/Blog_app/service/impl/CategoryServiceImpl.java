package com.blog.Blog_app.service.impl;

import com.blog.Blog_app.domain.entities.Category;
import com.blog.Blog_app.repository.CategoryRepository;
import com.blog.Blog_app.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Override
    public void deleteCategory(UUID id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Category does not exist with Id : " + id)
        );
        if (!category.getPosts().isEmpty()) throw new IllegalStateException("Category has post associated with it");
        categoryRepository.deleteById(id);
    }

    @Override
    public Category getCategoryById(UUID categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() ->
               new EntityNotFoundException("Category Not Found with id : "+ categoryId));
    }
}
