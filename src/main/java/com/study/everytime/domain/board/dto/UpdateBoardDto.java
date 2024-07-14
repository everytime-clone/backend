package com.study.everytime.domain.board.dto;

public class UpdateBoardDto {

    public record Inform(
            String name,
            String description
    ) {
    }

    public record Admin(
            String username
    ) {
    }

}
