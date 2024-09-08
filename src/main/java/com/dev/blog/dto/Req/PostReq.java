package com.dev.blog.dto.Req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PostReq {
    private String title;
    private String content;
    private String imageUrl;
}
