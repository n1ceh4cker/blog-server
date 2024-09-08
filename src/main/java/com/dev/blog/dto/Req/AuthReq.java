package com.dev.blog.dto.Req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthReq {
    private String email;
    private String password;
}
