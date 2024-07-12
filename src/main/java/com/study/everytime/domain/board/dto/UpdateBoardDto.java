package com.study.everytime.domain.board.dto;

public record UpdateBoardDto(
        Long boardId,
        String name,
        String description
) {
}
