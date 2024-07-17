package com.study.everytime.domain.board.service;

import com.study.everytime.domain.board.dto.ReadBoardDto;
import com.study.everytime.domain.board.dto.CreateBoardDto;
import com.study.everytime.domain.board.dto.UpdateBoardDto;
import com.study.everytime.domain.board.entity.Board;
import com.study.everytime.domain.board.exception.BoardException;
import com.study.everytime.domain.board.repository.BoardRepository;
import com.study.everytime.domain.user.entity.User;
import com.study.everytime.domain.user.exception.UserException;
import com.study.everytime.domain.user.repository.UserRepository;
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
    private final UserRepository userRepository;

    public void createBoard(Long userId, CreateBoardDto dto) {
        User user = getUser(userId);
        Board board = new Board(dto.name(), dto.description(), user);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public ReadBoardDto readBoard(Long boardId) {
        Board board = getBoard(boardId);
        return new ReadBoardDto(board.getName(), board.getDescription());
    }

    @Transactional(readOnly = true)
    public Slice<ReadBoardDto> searchBoard(String name, Pageable pageable) {
        return boardRepository.findByNameContains(name, pageable)
                .map(ReadBoardDto::from);
    }

    public void updateBoardInform(Long userId, Long boardId, UpdateBoardDto.Inform dto) {
        Board board = getBoard(boardId);
        checkAuthority(userId, board);
        board.updateInform(dto.name(), dto.description());
    }

    public void updateBoardAdmin(Long userId, Long boardId, UpdateBoardDto.Admin dto) {
        Board board = getBoard(boardId);
        checkAuthority(userId, board);

        User newAdmin = getUserByUsername(dto.username());
        board.updateAdmin(newAdmin);
    }

    public void deleteBoard(Long userId, Long boardId) {
        Board board = getBoard(boardId);
        checkAuthority(userId, board);
        boardRepository.delete(board);
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(UserException.UserNotFoundException::new);
    }

    private User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(UserException.UserNotFoundException::new);
    }

    private Board getBoard(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(BoardException.BoardNotFoundException::new);
    }

    private void checkAuthority(Long userId, Board board) {
        if (!board.getAdmin().getId().equals(userId)) {
            throw new BoardException.BoardAuthException();
        }
    }

}
