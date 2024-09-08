package com.dev.blog.dto.Res;

import com.dev.blog.data.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LikeRes {
    private Post post;
    private boolean like;
}
