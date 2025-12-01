package com.blog.Blog_app.service.impl;

import com.blog.Blog_app.domain.entities.User;
import com.blog.Blog_app.repository.UserRepository;
import com.blog.Blog_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User Not found with id : " + id)
        );
    }
}
