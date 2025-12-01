package com.blog.Blog_app.domain.dto.post;

import com.blog.Blog_app.domain.PostStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.Member;
import org.aspectj.weaver.ast.Not;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePostRequestDto {

    @NotBlank(message = "Title is required")
    @Size(min = 3,max = 200, message = "Title must be in between {min} and {max}")
    private String title;

    @NotBlank(message = "Content is required")
    @Size(min = 10,max = 50000,message = "Content must be between {min} and {max} characters")
    private String content;

    @NotNull(message = "CategoryId is required")
    private UUID categoryId;

    @Builder.Default
    @Size(max = 10, message = "Maximum of {max} tags are allowed")
    private Set<UUID> tagIds = new HashSet<>();

    @NotNull(message = "Status is reqired")
    private PostStatus status;
}
