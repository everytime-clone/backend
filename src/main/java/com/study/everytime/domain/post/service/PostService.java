package com.study.everytime.domain.post.service;

import com.study.everytime.domain.board.entity.Board;
import com.study.everytime.domain.board.exception.BoardException;
import com.study.everytime.domain.board.repository.BoardRepository;
import com.study.everytime.domain.post.dto.CreatePostDto;
import com.study.everytime.domain.post.dto.ReadPostDto;
import com.study.everytime.domain.post.dto.UpdatePostDto;
import com.study.everytime.domain.post.entity.Post;
import com.study.everytime.domain.post.exception.PostException;
import com.study.everytime.domain.post.repository.PostRepository;
import com.study.everytime.domain.user.entity.User;
import com.study.everytime.domain.user.exception.UserException;
import com.study.everytime.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public void createPost(Long userId, Long boardId, CreatePostDto dto) {
        User user = getUser(userId);
        Board board = getBoard(boardId);

        log.info("title: {}, content: {}, question: {}, anonymous: {}", dto.title(), dto.content(), dto.question(), dto.anonymous());

        Post post = new Post(dto.title(), dto.content(), dto.question(), dto.anonymous(), user, board);
        postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public ReadPostDto readPost(Long postId) {
        return postRepository.findReadPostDtoById(postId)
                .orElseThrow(PostException.PostNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public Slice<ReadPostDto> readPostPage(Long boardId, Pageable pageable) {
        return postRepository.findByBoard_Id(boardId, pageable);
    }

    public void updatePost(Long userId, Long postId, UpdatePostDto dto) {
        Post post = getPost(postId);

        if (!post.getWriter().getId().equals(userId)) {
            throw new PostException.PostAuthException();
        }

        post.update(dto.title(), dto.content());
    }

    public void deletePost(Long userId, Long postId) {
        Post post = getPost(postId);

        if (!post.getWriter().getId().equals(userId)) {
            throw new PostException.PostAuthException();
        }

        if (post.getQuestion()) {
            throw new PostException.PostQuestionDeleteException();
        }

        postRepository.delete(post);
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(UserException.UserNotFoundException::new);
    }

    private Post getPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(PostException.PostNotFoundException::new);
    }

    private Board getBoard(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(BoardException.BoardNotFoundException::new);
    }

}
