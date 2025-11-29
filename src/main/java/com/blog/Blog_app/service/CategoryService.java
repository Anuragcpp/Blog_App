package com.blog.Blog_app.service;

import com.blog.Blog_app.domain.entities.Category;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {
    List<Category> listCategories();
    Category createCategory(Category category);
}
