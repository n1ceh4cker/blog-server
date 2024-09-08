package com.dev.blog.controller;

import com.dev.blog.data.entity.Post;
import com.dev.blog.dto.Req.CommentReq;
import com.dev.blog.dto.Res.BaseRes;
import com.dev.blog.dto.Res.LikeRes;
import com.dev.blog.dto.Req.PostReq;
import com.dev.blog.exception.BlogException;
import com.dev.blog.message.Message;
import com.dev.blog.service.IPostService;
import com.dev.blog.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Controller
@RequestMapping("api/post")
@Validated
public class PostController {
    @Autowired
    IPostService postService;
    @Autowired
    Message message;
    @GetMapping("all")
    @ResponseBody
    public ResponseEntity<BaseRes> getPosts(){
        List<Post> posts = postService.getPosts();
        return ResponseUtils.getResponseEntity(posts, message.get("post.success.get"));
    }

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<BaseRes> getCurrentUserPosts(){
        List<Post> posts = postService.getCurrentUserPosts();
        return ResponseUtils.getResponseEntity(posts, message.get("post.success.get"));
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<BaseRes> addPost(@RequestBody PostReq postReq) {
        Post post = postService.addPost(postReq);
        return ResponseUtils.getResponseEntity(post, message.get("post.success.add"));
    }

    @PutMapping("{id}")
    @ResponseBody
    public ResponseEntity<BaseRes> updatePost(@PathVariable("id") String id, @RequestBody PostReq postReq) throws BlogException {
        Post post = postService.updatePost(postReq, id);
        return ResponseUtils.getResponseEntity(post, message.get("post.success.update"));
    }

    @PostMapping("{id}/like")
    @ResponseBody
    public ResponseEntity<BaseRes> likePost(@PathVariable("id") String id) throws BlogException {
        LikeRes likeRes = postService.likePost(id);
        if(likeRes.isLike()) {
            return ResponseUtils.getResponseEntity(likeRes.getPost(), message.get("post.success.like.add"));
        }
        else {
            return ResponseUtils.getResponseEntity(likeRes.getPost(), message.get("post.success.like.delete"));
        }
    }

    @PostMapping("{id}/comment")
    @ResponseBody
    public ResponseEntity<BaseRes> commentPost(@PathVariable("id") String id, @RequestBody CommentReq commentReq) throws BlogException {
        Post post = postService.commentPost(commentReq, id);
        return ResponseUtils.getResponseEntity(post, message.get("post.success.comment.add"));
    }
}
