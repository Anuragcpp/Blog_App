package com.blog.Blog_app.service.impl;

import com.blog.Blog_app.domain.PostStatus;
import com.blog.Blog_app.domain.dto.post.CreatePostRequest;
import com.blog.Blog_app.domain.dto.post.UpdatePostRequest;
import com.blog.Blog_app.domain.entities.Category;
import com.blog.Blog_app.domain.entities.Post;
import com.blog.Blog_app.domain.entities.Tag;
import com.blog.Blog_app.domain.entities.User;
import com.blog.Blog_app.repository.PostRepository;
import com.blog.Blog_app.service.CategoryService;
import com.blog.Blog_app.service.PostService;
import com.blog.Blog_app.service.TagService;
import com.blog.Blog_app.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    @Transactional(readOnly = true)
    @Override
    public List<Post> getAllPosts(UUID categoryId, UUID tagId) {
        if (categoryId != null && tagId != null ){
            Category category = categoryService.getCategoryById(categoryId);
            Tag tag = tagService.getTagById(tagId);
            return postRepository.findAllByPostStatusAndCategoryAndTagsContaining(
                    PostStatus.PUBLISHED,
                    category,
                    tag
            );
        }

        if(categoryId != null) {
            Category category = categoryService.getCategoryById(categoryId);
            return postRepository.findAllByPostStatusAndCategory(
                    PostStatus.PUBLISHED,
                    category
            );
        }

        if (tagId != null ) {
            Tag tag = tagService.getTagById(tagId);
            return postRepository.findAllByPostStatusAndTagsContaining(
                    PostStatus.PUBLISHED,
                    tag
            );
        }

        return postRepository.findAllByPostStatus(PostStatus.PUBLISHED);
    }

    @Override
    public List<Post> getDrafts(UUID userId) {
        User user = userService.getUserById(userId);
        return postRepository.findAllByAuthorAndPostStatus(user,PostStatus.DRAFT);
    }

    @Transactional
    @Override
    public Post createPost(UUID userId, CreatePostRequest createPostRequest) {
        User user = userService.getUserById(userId);
        Category category = categoryService.getCategoryById(createPostRequest.getCategoryId());
        List<Tag> tags = tagService.getTagsByIds(createPostRequest.getTagIds());
        Post post = Post.builder()
                .Title(createPostRequest.getTitle())
                .content(createPostRequest.getContent())
                .author(user)
                .postStatus(createPostRequest.getStatus())
                .category(category)
                .tags(new HashSet<>(tags))
                .readingTime(10)
                .build();
        return postRepository.save(post);
    }

    @Transactional
    @Override
    public Post updatePost(UpdatePostRequest updatePostRequest) {
        Post post = postRepository.findById(updatePostRequest.getId()).orElseThrow(
                () -> new EntityNotFoundException("Post not found with id : " + updatePostRequest.getId())
        );


        post.setTitle(updatePostRequest.getTitle());
        post.setContent(updatePostRequest.getContent());
        if ( !post.getCategory().getId().equals(updatePostRequest.getCategoryId())){
            Category category = categoryService.getCategoryById(updatePostRequest.getCategoryId());
            post.setCategory(category);
        }
        Set<UUID> existingTags = post.getTags().stream().map(Tag::getId).collect(Collectors.toSet());
        if ( !existingTags.equals(updatePostRequest.getTagIds())) {
            List<Tag> tags = tagService.getTagsByIds(updatePostRequest.getTagIds());
            post.setTags(new HashSet<>(tags));
        }
        post.setPostStatus(updatePostRequest.getPostStatus());
        return postRepository.save(post);
    }

    @Transactional
    @Override
    public void deletePost(UUID postId) {
        Post post =  getPostById(postId);
        postRepository.delete(post);
    }

    @Override
    public Post getPostById(UUID postId) {
        return postRepository.findById(postId).orElseThrow(()->
                new EntityNotFoundException("No Post Found Associated with id : " + postId)
                );

    }


}
