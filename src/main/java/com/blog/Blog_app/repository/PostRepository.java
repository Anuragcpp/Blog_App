package com.blog.Blog_app.repository;

import com.blog.Blog_app.domain.PostStatus;
import com.blog.Blog_app.domain.entities.Category;
import com.blog.Blog_app.domain.entities.Post;
import com.blog.Blog_app.domain.entities.Tag;
import com.blog.Blog_app.domain.entities.User;
import com.blog.Blog_app.service.PostService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findAllByPostStatusAndCategoryAndTagsContaining(PostStatus status, Category category , Tag tag);
    List<Post> findAllByPostStatusAndCategory(PostStatus status, Category category);
    List<Post> findAllByPostStatusAndTagsContaining(PostStatus status, Tag tag);
    List<Post> findAllByPostStatus(PostStatus status);
    List<Post> findAllByAuthorAndPostStatus(User user, PostStatus postStatus);
}
