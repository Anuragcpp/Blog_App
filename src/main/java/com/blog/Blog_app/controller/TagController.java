package com.blog.Blog_app.controller;

import com.blog.Blog_app.domain.dto.respose.SuccessResponse;
import com.blog.Blog_app.domain.dto.tag.CreateTagsRequest;
import com.blog.Blog_app.domain.dto.tag.TagResponse;
import com.blog.Blog_app.domain.entities.Tag;
import com.blog.Blog_app.mapper.TagMapper;
import com.blog.Blog_app.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private TagMapper tagMapper;

    @GetMapping
    public ResponseEntity<SuccessResponse> getTags() {
        List<TagResponse> tags = tagService.getTags().stream()
                .map(tagMapper::toTagResponse)
                .toList();

        return new ResponseEntity<>(
                new SuccessResponse(
                        HttpStatus.OK.value(),
                        "Tags Fetched successfully",
                        tags
                ),
                HttpStatus.OK
        );

    }

    @PostMapping
    public ResponseEntity<SuccessResponse> createTags(
            @RequestBody CreateTagsRequest createTagsRequest
            ) {
        List<TagResponse> savedTags = tagService.createTags(createTagsRequest.getNames())
                .stream().map(tagMapper::toTagResponse)
                .toList();

        return new ResponseEntity<>(
                new SuccessResponse(
                        HttpStatus.OK.value(),
                        "Tag Names Saved successfully",
                        savedTags
                ),
                HttpStatus.OK
        );
    }
}
