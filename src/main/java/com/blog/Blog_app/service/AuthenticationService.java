package com.blog.Blog_app.service;

import com.blog.Blog_app.domain.dto.login.RegisterRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {
    UserDetails authenticate(String email,String password);
    String generateToken(UserDetails userDetails);
    UserDetails validateToken(String token);
    void register(RegisterRequest request);
}
