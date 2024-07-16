package com.study.everytime.domain.board.controller;

import com.study.everytime.domain.board.dto.CreateBoardDto;
import com.study.everytime.domain.board.dto.ReadBoardDto;
import com.study.everytime.domain.board.dto.SearchBoardDto;
import com.study.everytime.domain.board.dto.UpdateBoardDto;
import com.study.everytime.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public void createBoard(@AuthenticationPrincipal Long userId, @RequestBody CreateBoardDto dto) {
        boardService.createBoard(userId, dto);
    }

    @GetMapping("/{boardId}")
    public ReadBoardDto readBoard(@PathVariable Long boardId) {
        return boardService.readBoard(boardId);
    }

    @GetMapping
    public SearchBoardDto searchBoard(@RequestParam String name, Pageable pageable) {
        return boardService.searchBoard(name, pageable);
    }

    @PatchMapping("/{boardId}/inform")
    public void updateBoardInform(@AuthenticationPrincipal Long userId,
                                  @PathVariable Long boardId,
                                  @RequestBody UpdateBoardDto.Inform dto) {
        boardService.updateBoardInform(userId, boardId, dto);
    }

    @PatchMapping("/{boardId}/admin")
    public void updateBoardAdmin(@AuthenticationPrincipal Long userId,
                                 @PathVariable Long boardId,
                                 @RequestBody UpdateBoardDto.Admin dto) {
        boardService.updateBoardAdmin(userId, boardId, dto);
    }

    @DeleteMapping("/{boardId}")
    public void deleteBoard(@AuthenticationPrincipal Long userId, @PathVariable Long boardId) {
        boardService.deleteBoard(userId, boardId);
    }

}
