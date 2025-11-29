package com.blog.Blog_app.domain.dto.respose;

public record SuccessResponse(
        int status,
        String message,
        Object data
) {
}
