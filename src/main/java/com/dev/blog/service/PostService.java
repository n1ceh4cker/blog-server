package com.dev.blog.service;

import com.dev.blog.data.entity.*;
import com.dev.blog.data.repository.PostRepository;
import com.dev.blog.dto.Req.CommentReq;
import com.dev.blog.dto.Res.LikeRes;
import com.dev.blog.dto.Req.PostReq;
import com.dev.blog.exception.BlogException;
import com.dev.blog.message.Message;
import com.dev.blog.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService{
    @Autowired
    PostRepository repository;
    @Autowired
    ProfileService service;
    @Autowired
    Message message;
    @Override
    public List<Post> getPosts() {
        return repository.findAll();
    }

    @Override
    public List<Post> getCurrentUserPosts() {
        User currentUser = AuthUtils.getLoggedInUser();
        return repository.findByAuthorId(currentUser.getId());
    }

    @Override
    public Post addPost(PostReq post) {
        User currentUser = AuthUtils.getLoggedInUser();
        Post newPost = Post.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .author(currentUser)
                .createdAt(new Date())
                .likes(new ArrayList<>())
                .comments(new ArrayList<>())
                .build();
        repository.save(newPost);
        return newPost;
    }

    @Override
    public Post updatePost(PostReq postReq, String postId) throws BlogException {
        User currentUser = AuthUtils.getLoggedInUser();
        Post post = repository.findById(postId)
                .orElseThrow();
        if(!post.getAuthor().getId().equals(currentUser.getId())) {
            throw new BlogException(message.get("post.error.update"));
        }
        post.setTitle(postReq.getTitle());
        post.setContent(postReq.getContent());
        post.setImageUrl(postReq.getImageUrl());
        repository.save(post);
        return post;
    }

    @Override
    public LikeRes likePost(String postId) throws BlogException {
        User currentUser = AuthUtils.getLoggedInUser();
        LikeRes likeRes;
        Post post = repository.findById(postId)
                .orElseThrow(() -> new BlogException(message.get("post.error.get")));
        Like newlike = new Like(currentUser);
        if(post.getLikes().stream().anyMatch(like -> like.getAuthor().getId().equals(currentUser.getId()))){
            List<Like> filteredLike = post.getLikes().stream().filter(like -> !like.getAuthor().getId().equals(currentUser.getId())).collect(Collectors.toList());
            post.setLikes(filteredLike);
            likeRes = new LikeRes(post, false);
        }
        else{
            post.getLikes().add(newlike);
            likeRes = new LikeRes(post, true);
        }
        repository.save(post);
        return likeRes;
    }

    @Override
    public Post commentPost(CommentReq comment, String postId) throws BlogException {
        Profile currentUser = service.getCurentUserProfile();
        Post post = repository.findById(postId)
                .orElseThrow(() -> new BlogException(message.get("post.error.get")));
        Comment newComment = new Comment(comment.getComment(), new Date(), currentUser);
        post.getComments().add(newComment);
        repository.save(post);
        return post;
    }
}
