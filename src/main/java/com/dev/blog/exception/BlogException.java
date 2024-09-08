package com.dev.blog.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BlogException extends Exception{
    private HttpStatus status;
    public BlogException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }
    public BlogException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
