package com.study.everytime.domain.board.dto;

import com.study.everytime.domain.board.entity.Board;

public record ReadBoardDto(
        String name,
        String description
) {

    public static ReadBoardDto from(Board board) {
        return new ReadBoardDto(board.getName(), board.getDescription());
    }

}
