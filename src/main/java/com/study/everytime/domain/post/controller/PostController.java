package com.study.everytime.domain.post.controller;

import com.study.everytime.domain.post.dto.*;
import com.study.everytime.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/board/{boardId}/post")
    public void createPost(@AuthenticationPrincipal Long userId, @PathVariable Long boardId, @RequestBody CreatePostDto dto) {
        postService.createPost(userId, boardId, dto);
    }

    @GetMapping("/board/{boardId}/post")
    public Slice<BoardPostPageDto> readBoardPosts(@PathVariable Long boardId, Pageable pageable) {
        return postService.readBoardPosts(boardId, pageable);
    }

    @GetMapping("/post/{postId}")
    public ReadPostDto readPost(@AuthenticationPrincipal Long userId, @PathVariable Long postId) {
        return postService.readPost(userId, postId);
    }

    @PatchMapping("/post/{postId}")
    public void updatePost(@AuthenticationPrincipal Long userId, @PathVariable Long postId, @RequestBody UpdatePostDto dto) {
        postService.updatePost(userId, postId, dto);
    }

    @DeleteMapping("/post/{postId}")
    public void deletePost(@AuthenticationPrincipal Long userId, @PathVariable Long postId) {
        postService.deletePost(userId, postId);
    }

    @PostMapping("/post/{postId}/like")
    public void addLike(@AuthenticationPrincipal Long userId, @PathVariable Long postId) {
        postService.addLike(userId, postId);
    }

    @PostMapping("/post/{postId}/scrap")
    public void addScrap(@AuthenticationPrincipal Long userId, @PathVariable Long postId) {
        postService.addScrap(userId, postId);
    }

    @DeleteMapping("/post/{postId}/scrap")
    public void calcelScrap(@AuthenticationPrincipal Long userId, @PathVariable Long postId) {
        postService.cancelScrap(userId, postId);
    }

    @GetMapping("/post/mypost")
    public Slice<MyPostPageDto> readMyPosts(@AuthenticationPrincipal Long userId, Pageable pageable) {
        return postService.readMyPosts(userId, pageable);
    }

    @GetMapping("/post/myscrab")
    public Slice<MyPostPageDto> readMyScrabs(@AuthenticationPrincipal Long userId, Pageable pageable) {
        return postService.readMyScrabs(userId, pageable);
    }

    @GetMapping("/post/hotpost")
    public Slice<MyPostPageDto> readHotPosts(Pageable pageable) {
        return postService.readHotPosts(pageable);
    }

}
