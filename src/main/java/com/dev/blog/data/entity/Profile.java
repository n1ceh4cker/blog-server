package com.dev.blog.data.entity;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "profiles")
public class Profile {
    @Id
    private String id;
    private String username;
    private String bio;
    private String imageUrl;
    @DocumentReference(lazy = true)
    @JsonIncludeProperties("id")
    private User creator;
}
