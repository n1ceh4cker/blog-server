package com.dev.blog.exception;

import com.dev.blog.dto.Res.BaseRes;
import com.dev.blog.message.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    Message message;

    @ExceptionHandler({BlogException.class, AuthenticationException.class})
    protected ResponseEntity<Object> handleException(
            BlogException ex, WebRequest request){
        BaseRes baseRes =
                new BaseRes<>(false, ex.getMessage(), null);
        return handleExceptionInternal(
                ex, baseRes, HttpHeaders.EMPTY, ex.getStatus(), request);
    }

}

