package com.study.everytime.domain.bookmark.controller;

import com.study.everytime.domain.bookmark.dto.ReadBookmarkListDto;
import com.study.everytime.domain.bookmark.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookmark")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping("/{boardId}")
    public void addBookmark(@AuthenticationPrincipal Long userId, @PathVariable Long boardId) {
        bookmarkService.createBookmark(userId, boardId);
    }

    @GetMapping
    public ReadBookmarkListDto readBookmark(@AuthenticationPrincipal Long userId) {
        return bookmarkService.readAllBookmarks(userId);
    }

    @DeleteMapping("/{boardId}")
    public void removeBookmark(@AuthenticationPrincipal Long userId, @PathVariable Long boardId) {
        bookmarkService.deleteBookmark(userId, boardId);
    }

}
