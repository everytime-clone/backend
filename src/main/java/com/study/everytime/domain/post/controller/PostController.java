package com.study.everytime.domain.post.controller;

import com.study.everytime.domain.post.dto.CreatePostDto;
import com.study.everytime.domain.post.dto.ReadPostDto;
import com.study.everytime.domain.post.dto.UpdatePostDto;
import com.study.everytime.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board/{boardId}/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public void createPost(@AuthenticationPrincipal Long userId, @PathVariable Long boardId, @RequestBody CreatePostDto dto) {
        postService.createPost(userId, boardId, dto);
    }

    @GetMapping("/{postId}")
    public ReadPostDto readPost(@PathVariable Long postId) {
        return postService.readPost(postId);
    }

    @GetMapping
    public Slice<ReadPostDto> readPostPage(@PathVariable Long boardId, Pageable pageable) {
        return postService.readPostPage(boardId, pageable);
    }

    @PatchMapping("/{postId}")
    public void updatePost(@AuthenticationPrincipal Long userId, @PathVariable Long postId, @RequestBody UpdatePostDto dto) {
        postService.updatePost(userId, postId, dto);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@AuthenticationPrincipal Long userId, @PathVariable Long postId) {
        postService.deletePost(userId, postId);
    }

}
