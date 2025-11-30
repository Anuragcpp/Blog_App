package com.blog.Blog_app.service;

import com.blog.Blog_app.domain.entities.Tag;

import java.util.List;
import java.util.Set;

public interface TagService {
    List<Tag> getTags();
    List<Tag> createTags(Set<String> tagNames);
}
