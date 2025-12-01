package com.blog.Blog_app.mapper;

import com.blog.Blog_app.domain.dto.post.*;
import com.blog.Blog_app.domain.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(target = "author", source = "author")
    @Mapping(target = "category",source = "category")
    @Mapping(target = "tags",source = "tags ")
    PostDto toDto(Post post);

    @Mapping(target = "tagIds", source = "tagIds")
    CreatePostRequest toCreatePostRequest(CreatePostRequestDto createPostRequestDto);

    @Mapping(target = "tagIds",source = "tagIds")
    UpdatePostRequest toUpdatePostRequest(UpdatePostRequestDto updatePostRequestDto);
}
