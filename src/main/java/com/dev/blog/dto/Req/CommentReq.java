package com.dev.blog.dto.Req;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CommentReq {
    private String comment;
    private String extras;
}
