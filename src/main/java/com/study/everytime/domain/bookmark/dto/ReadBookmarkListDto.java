package com.study.everytime.domain.bookmark.dto;

import com.study.everytime.domain.board.entity.Board;
import com.study.everytime.domain.bookmark.entity.Bookmark;

import java.util.List;

public record ReadBookmarkListDto(
        List<ReadBookmarkDto> bookmarks
) {

    public record ReadBookmarkDto(
            Long boardId,
            String name,
            String description
    ) {

        public static ReadBookmarkDto from(Bookmark bookmark) {
            Board board = bookmark.getBoard();
            return new ReadBookmarkDto(board.getId(), board.getName(), board.getDescription());
        }

    }

    public static ReadBookmarkListDto from(List<Bookmark> bookmarks) {
        return new ReadBookmarkListDto(
                bookmarks.stream()
                        .map(ReadBookmarkDto::from)
                        .toList());
    }

}
