package com.dev.blog.data.entity;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;

@Data
@AllArgsConstructor
public class Comment {
    private String comment;
    private Date createdAt;
    @DocumentReference(lazy = true)
    @JsonIncludeProperties({"id", "username"})
    private Profile author;
}
