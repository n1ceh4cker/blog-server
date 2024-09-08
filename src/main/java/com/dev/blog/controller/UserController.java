package com.dev.blog.controller;

import com.dev.blog.data.entity.User;
import com.dev.blog.dto.Res.BaseRes;
import com.dev.blog.dto.Res.LoginRes;
import com.dev.blog.dto.Req.AuthReq;
import com.dev.blog.exception.BlogException;
import com.dev.blog.message.Message;
import com.dev.blog.service.IUserService;
import com.dev.blog.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api/auth")
@Validated
public class UserController {
    @Autowired
    IUserService userService;
    @Autowired
    Message message;
    @PostMapping("signup")
    @ResponseBody
    public ResponseEntity<BaseRes> signup(@RequestBody AuthReq authReq) throws BlogException {
        User user = userService.signup(authReq);
        return ResponseUtils.getResponseEntity(user, message.get("user.success.signup"));
    }

    @PostMapping("login")
    @ResponseBody
    public ResponseEntity<BaseRes> login(@RequestBody AuthReq authReq) throws BlogException {
        LoginRes loginRes = userService.login(authReq);
        return ResponseUtils.getResponseEntity(loginRes, message.get("user.success.login"));
    }
}
