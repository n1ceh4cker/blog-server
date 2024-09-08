package com.dev.blog.utils;

import com.dev.blog.data.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtils {
    public static User getLoggedInUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
