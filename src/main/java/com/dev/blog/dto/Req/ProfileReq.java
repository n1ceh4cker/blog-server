package com.dev.blog.dto.Req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProfileReq {
    private String username;
    private String bio;
    private String imageUrl;
}
