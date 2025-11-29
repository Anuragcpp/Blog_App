package com.blog.Blog_app.mapper;

import com.blog.Blog_app.domain.PostStatus;
import com.blog.Blog_app.domain.dto.category.CategoryDto;
import com.blog.Blog_app.domain.dto.category.CreateCategoryRequest;
import com.blog.Blog_app.domain.entities.Category;
import com.blog.Blog_app.domain.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(target = "postCount" ,source = "posts", qualifiedByName = "calculatePostCount")
    CategoryDto toDto(Category category);

    Category toEntity(CreateCategoryRequest createCategoryRequest);

    @Named("calculatePostCount")
    default long calculatePostCount(List<Post> posts) {
        if( posts == null ) return 0;
        return posts.stream()
                .filter(post -> PostStatus.PUBLISHED.equals(post.getPostStatus()))
                .count();
    }
}
