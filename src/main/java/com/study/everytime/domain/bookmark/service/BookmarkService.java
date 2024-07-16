package com.study.everytime.domain.bookmark.service;

import com.study.everytime.domain.board.entity.Board;
import com.study.everytime.domain.board.exception.BoardException;
import com.study.everytime.domain.board.repository.BoardRepository;
import com.study.everytime.domain.bookmark.dto.ReadBookmarkListDto;
import com.study.everytime.domain.bookmark.entity.Bookmark;
import com.study.everytime.domain.bookmark.exception.BookmarkException;
import com.study.everytime.domain.bookmark.repository.BookmarkRepository;
import com.study.everytime.domain.user.entity.User;
import com.study.everytime.domain.user.exception.UserException;
import com.study.everytime.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public void createBookmark(Long userId, Long boardId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserException.UserNotFoundException::new);
        Board board = boardRepository.findById(boardId)
                .orElseThrow(BoardException.BoardNotFoundException::new);

        Bookmark bookmark = new Bookmark(user, board);
        bookmarkRepository.save(bookmark);
    }

    public ReadBookmarkListDto readAllBookmarks(Long userId) {
        List<Bookmark> bookmarks = bookmarkRepository.findByUser_Id(userId);
        return ReadBookmarkListDto.from(bookmarks);
    }

    public void deleteBookmark(Long userId, Long boardId) {
        Bookmark bookmark = bookmarkRepository.findByUser_IdAndBoard_Id(userId, boardId)
                .orElseThrow(BookmarkException.BookmarkNotFoundException::new);
        bookmarkRepository.delete(bookmark);
    }

}
