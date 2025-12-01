package com.blog.Blog_app.controller;

import com.blog.Blog_app.domain.dto.post.CreatePostRequestDto;
import com.blog.Blog_app.domain.dto.post.PostDto;
import com.blog.Blog_app.domain.dto.post.UpdatePostRequestDto;
import com.blog.Blog_app.domain.dto.respose.SuccessResponse;
import com.blog.Blog_app.domain.entities.Post;
import com.blog.Blog_app.mapper.PostMapper;
import com.blog.Blog_app.service.PostService;
import com.blog.Blog_app.utils.HexToUUIDConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostMapper postMapper;

    @GetMapping
    public ResponseEntity<SuccessResponse> getAllPosts(
            @RequestParam(required = false) String categoryId,
            @RequestParam(required = false) String tagId
            ) {
        UUID categoryUuid = HexToUUIDConverter.hexToUUID(categoryId);
        UUID tagUuid = HexToUUIDConverter.hexToUUID(tagId);
        List<PostDto> postDtos = postService.getAllPosts(categoryUuid,tagUuid).stream()
                .map(postMapper::toDto)
                .toList();

        return new ResponseEntity<>(
                new SuccessResponse(
                        HttpStatus.OK.value(),
                        "Post data Fetched Successfully",
                        postDtos
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/drafts")
    public ResponseEntity<SuccessResponse> getDrafts(
            @RequestAttribute UUID userId
    ) {
        List<PostDto> postDtos = postService.getDrafts(userId)
                .stream()
                .map(postMapper::toDto)
                .toList();
        return new ResponseEntity<>(
                new SuccessResponse(
                  HttpStatus.OK.value(),
                  "Draft Post Fetched Successfully",
                        postDtos
                ),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<SuccessResponse> createPost(
            @RequestBody CreatePostRequestDto createPostRequestDto,
            @RequestAttribute UUID userId
            ){
        Post post = postService.createPost(userId,postMapper.toCreatePostRequest(createPostRequestDto));
        return new ResponseEntity<>(
                new SuccessResponse(
                        HttpStatus.CREATED.value(),
                        "Post Created Successfully",
                        postMapper.toDto(post)
                ),
                HttpStatus.CREATED
        );
    }

    @PutMapping
    public ResponseEntity<SuccessResponse> updatePost(
            @RequestBody UpdatePostRequestDto updatePostRequestDto
            ) {
        Post post = postService.updatePost(postMapper.toUpdatePostRequest(updatePostRequestDto));
        return new ResponseEntity<>(
                new SuccessResponse(
                        HttpStatus.OK.value(),
                        "Post Updated successfully",
                        postMapper.toDto(post)
                ),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{post_id}")
    public ResponseEntity<SuccessResponse> deletePost(
            @PathVariable(name = "post_id") UUID postId
    ) {
        postService.deletePost(postId);
        return new ResponseEntity<>(
                new SuccessResponse(
                        HttpStatus.OK.value(),
                        "Post Deleted Successfully",
                        null
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/{post_id}")
    public ResponseEntity<SuccessResponse> getPostById(
            @PathVariable(name = "post_id") UUID postId
    ) {
        Post post = postService.getPostById(postId);
        return new ResponseEntity<>(
                new SuccessResponse(
                        HttpStatus.OK.value(),
                        "Post Retirved Successfully",
                        postMapper.toDto(post)
                ),
                HttpStatus.OK
        );
    }

}
