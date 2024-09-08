package com.dev.blog.service;

import com.dev.blog.data.entity.User;
import com.dev.blog.dto.Res.LoginRes;
import com.dev.blog.dto.Req.AuthReq;
import com.dev.blog.exception.BlogException;

public interface IUserService {
    User signup(AuthReq user) throws BlogException;
    LoginRes login(AuthReq user) throws BlogException;
}
