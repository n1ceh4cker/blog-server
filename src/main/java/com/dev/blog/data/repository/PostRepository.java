package com.dev.blog.data.repository;

import com.dev.blog.data.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findByAuthorId(String userId);
}
