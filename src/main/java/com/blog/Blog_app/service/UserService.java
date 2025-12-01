package com.blog.Blog_app.service;

import com.blog.Blog_app.domain.entities.User;

import java.util.UUID;

public interface UserService {
    User getUserById(UUID id);
}
