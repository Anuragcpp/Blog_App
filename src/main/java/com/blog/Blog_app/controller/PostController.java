package com.blog.Blog_app.controller;

import com.blog.Blog_app.domain.dto.post.PostDto;
import com.blog.Blog_app.domain.dto.respose.SuccessResponse;
import com.blog.Blog_app.mapper.PostMapper;
import com.blog.Blog_app.service.PostService;
import com.blog.Blog_app.utils.HexToUUIDConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
