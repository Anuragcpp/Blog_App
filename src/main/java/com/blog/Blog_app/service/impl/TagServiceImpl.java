package com.blog.Blog_app.service.impl;

import com.blog.Blog_app.domain.entities.Tag;
import com.blog.Blog_app.repository.TagRepository;
import com.blog.Blog_app.service.TagService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<Tag> getTags() {
        return tagRepository.findAllWithPostCount();
    }

    @Transactional
    @Override
    public List<Tag> createTags(Set<String> tagNames) {
        List<Tag> existingTags = tagRepository.findByNameIn(tagNames);
        Set<String> existingTagNames = existingTags.stream()
                .map(Tag::getName)
                .collect(Collectors.toSet());

        List<Tag> newTags = tagNames.stream()
                .filter(name -> !existingTagNames.contains(name))
                .map(
                        name ->
                                Tag.builder()
                                        .name(name)
                                        .posts(new HashSet<>())
                                        .build()
                ).toList();

        List<Tag> savedTags = new ArrayList<>();
        if(!newTags.isEmpty()) savedTags = tagRepository.saveAll(newTags);
        return savedTags;
    }

    @Override
    public void deleteTag(UUID id) {
        tagRepository.findById(id).ifPresent( tag -> {
            if (!tag.getPosts().isEmpty()){
                throw new IllegalStateException("Cannot delete tag associated with posts");
            }
            tagRepository.deleteById(id);
        });
    }

    @Override
    public Tag getTagById(UUID id) {
        return tagRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("Tag not found with id : "+ id)
        );
    }
}
