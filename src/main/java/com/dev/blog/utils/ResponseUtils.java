package com.dev.blog.utils;

import com.dev.blog.dto.Res.BaseRes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {
    public static <T> ResponseEntity<BaseRes> getResponseEntity(T t, String message) {
        BaseRes baseRes = BaseRes.builder()
                .success(true)
                .message(message)
                .data(t)
                .build();
        return new ResponseEntity<BaseRes>(baseRes, HttpStatus.OK);
    }
}
