package com.blog.Blog_app.controller;

import com.blog.Blog_app.domain.dto.login.AuthResponse;
import com.blog.Blog_app.domain.dto.login.LoginRequest;
import com.blog.Blog_app.domain.dto.login.RegisterRequest;
import com.blog.Blog_app.domain.dto.respose.SuccessResponse;
import com.blog.Blog_app.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest loginRequest
            ) {

        UserDetails userDetails = authenticationService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        String token = authenticationService.generateToken(userDetails);
        return ResponseEntity.ok(
                AuthResponse.builder()
                        .token(token)
                        .expiresIn(84600)
                        .build()
        );
    }

    @PostMapping("/register")
    public ResponseEntity<SuccessResponse> register(
            @RequestBody RegisterRequest request
            ) {
        authenticationService.register(request);
        return new  ResponseEntity<>(
                new SuccessResponse(
                HttpStatus.CONTINUE.value(),
                "User Created Successfully",
                null
                ),
                HttpStatus.CREATED
        );
    }
}
