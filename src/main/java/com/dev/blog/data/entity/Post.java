package com.dev.blog.data.entity;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "posts")
public class Post {
    @Id
    private String id;
    private String title;
    private String content;
    private String imageUrl;
    private Date createdAt;
    @DocumentReference(lazy = true)
    @JsonIncludeProperties("id")
    private User author;
    private List<Like> likes;
    private List<Comment> comments;
}
