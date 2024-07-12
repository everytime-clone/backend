package com.study.everytime.domain.board.service;

import com.study.everytime.domain.board.dto.CreateBoardDto;
import com.study.everytime.domain.board.dto.ReadBoardDto;
import com.study.everytime.domain.board.dto.SearchBoardDto;
import com.study.everytime.domain.board.dto.UpdateBoardDto;
import com.study.everytime.domain.board.entity.Board;
import com.study.everytime.domain.board.exception.BoardException;
import com.study.everytime.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void createBoard(CreateBoardDto dto) {
        Board board = new Board(dto.name(), dto.description());
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public ReadBoardDto readBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(BoardException.BoardNotFoundException::new);
        return new ReadBoardDto(board.getName(), board.getDescription());
    }

    @Transactional(readOnly = true)
    public SearchBoardDto searchBoard(String name, Pageable pageable) {
        Slice<SearchBoardDto.BoardPageDto> slice = boardRepository.findByNameContains(name, pageable)
                .map(SearchBoardDto.BoardPageDto::from);
        return new SearchBoardDto(slice);
    }

    public void updateBoard(UpdateBoardDto dto) {
        Board board = boardRepository.findById(dto.boardId())
                .orElseThrow(BoardException.BoardNotFoundException::new);
        board.update(dto.name(), dto.description());
    }

    public void deleteBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(BoardException.BoardNotFoundException::new);
        boardRepository.delete(board);
    }

}
