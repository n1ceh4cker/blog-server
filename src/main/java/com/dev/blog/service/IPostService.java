package com.dev.blog.service;

import com.dev.blog.data.entity.Post;
import com.dev.blog.dto.Req.CommentReq;
import com.dev.blog.dto.Res.LikeRes;
import com.dev.blog.dto.Req.PostReq;
import com.dev.blog.exception.BlogException;

import java.util.List;

public interface IPostService {
    List<Post> getPosts();
    List<Post> getCurrentUserPosts();
    Post addPost(PostReq post);
    Post updatePost(PostReq post, String postId) throws BlogException;
    LikeRes likePost(String postId) throws BlogException;
    Post commentPost(CommentReq comment, String postId) throws BlogException;
}
