package com.dev.blog.dto.Res;

import com.dev.blog.data.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class LoginRes {
    private String token;
    private long expiresIn;
    private User user;
}
