package com.study.everytime.domain.board.controller;

import com.study.everytime.domain.board.dto.CreateBoardDto;
import com.study.everytime.domain.board.dto.ReadBoardDto;
import com.study.everytime.domain.board.dto.SearchBoardDto;
import com.study.everytime.domain.board.dto.UpdateBoardDto;
import com.study.everytime.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public void createBoard(@RequestBody CreateBoardDto dto) {
        boardService.createBoard(dto);
    }

    @GetMapping("/{boardId}")
    public ReadBoardDto readBoard(@PathVariable Long boardId) {
        return boardService.readBoard(boardId);
    }

    @GetMapping
    public SearchBoardDto searchBoard(@RequestParam String name, Pageable pageable) {
        return boardService.searchBoard(name, pageable);
    }

    @PatchMapping
    public void updateBoard(@RequestBody UpdateBoardDto dto) {
        boardService.updateBoard(dto);
    }

    @DeleteMapping("/{boardId}")
    public void deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
    }

}
