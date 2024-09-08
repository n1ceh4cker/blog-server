package com.dev.blog.dto.Res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BaseRes<T> {
    private boolean success;
    private String message;
    private T data;
}
