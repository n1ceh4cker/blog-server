package com.dev.blog.data.entity;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Data
@AllArgsConstructor
public class Like {
    @DocumentReference(lazy = true)
    @JsonIncludeProperties("id")
    private User author;
}
