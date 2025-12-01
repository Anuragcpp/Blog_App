package com.blog.Blog_app.service;

import com.blog.Blog_app.domain.dto.post.CreatePostRequest;
import com.blog.Blog_app.domain.dto.post.UpdatePostRequest;
import com.blog.Blog_app.domain.entities.Post;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> getAllPosts(UUID categoryId,UUID tagid);
    List<Post> getDrafts(UUID userId);
    Post createPost(UUID userId, CreatePostRequest createPostRequest);
    Post updatePost(UpdatePostRequest updatePostRequest);
}
