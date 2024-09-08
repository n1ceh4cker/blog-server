package com.dev.blog.service;

import com.dev.blog.data.entity.User;
import com.dev.blog.data.repository.UserRepository;

import com.dev.blog.dto.Res.LoginRes;
import com.dev.blog.dto.Req.AuthReq;
import com.dev.blog.exception.BlogException;
import com.dev.blog.message.Message;
import com.dev.blog.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService{
    @Value("${security.jwt.expiry}")
    private long jwtExpiry;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserRepository repository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtService;
    @Autowired
    Message message;
    @Override
    public User signup(AuthReq user) throws BlogException {
        Optional<User> existingUser = repository.findByEmail(user.getEmail());
        if(existingUser.isPresent()){
            throw new BlogException(message.get("user.error.signup"));
        }
        User newUser = User.builder()
                .email(user.getEmail())
                .password(encoder.encode(user.getPassword()))
                .roles("ROLE_USER")
                .enabled(true)
                .build();
        repository.save(newUser);
        return newUser;
    }

    @Override
    public LoginRes login(AuthReq user) throws BlogException {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            User loggedInUser = repository.findByEmail(user.getEmail())
                    .orElseThrow();
            String token = jwtService.generateToken(loggedInUser);
            return new LoginRes(token, jwtExpiry, loggedInUser);
        } catch (AuthenticationException e) {
            if(e.getMessage().equals("Bad credentials")) {
                throw new BlogException(message.get("user.error.login.password"),  HttpStatus.UNAUTHORIZED);
            }
            else {
                throw new BlogException(message.get("user.error.login.email"), HttpStatus.UNAUTHORIZED);
            }

        }
    }
}
