package com.study.everytime.domain.board.dto;

import com.study.everytime.domain.board.entity.Board;
import org.springframework.data.domain.Slice;

public record SearchBoardDto(
        Slice<BoardPageDto> pages
) {

    public record BoardPageDto(
            String name,
            String description
    ) {

        public static BoardPageDto from(Board board) {
            return new BoardPageDto(board.getName(), board.getDescription());
        }

    }

}
