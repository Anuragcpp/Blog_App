package com.blog.Blog_app.domain.dto.post;

import com.blog.Blog_app.domain.PostStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePostRequestDto {

    @NotNull(message = "Post id is required")
    private UUID id;

    @NotBlank(message = "Title is required")
    @Size(min = 3,max = 200, message = "Title should be between {min} and {max}")
    private String title;

    @NotBlank(message = "Content is required")
    @Size(min = 10,max = 50000,message = "Content should be between {min} and {max}")
    private String content;

    @NotNull(message = "category id is required")
    private UUID categoryId;

    @NotNull(message = "tag ids are required")
    private Set<UUID> tagIds;

    @NotNull(message = "status is required")
    private PostStatus postStatus;
}
