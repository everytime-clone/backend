package com.study.everytime.domain.post.service;

import com.study.everytime.domain.board.entity.Board;
import com.study.everytime.domain.board.exception.BoardException;
import com.study.everytime.domain.board.repository.BoardRepository;
import com.study.everytime.domain.post.dto.*;
import com.study.everytime.domain.post.entity.Like;
import com.study.everytime.domain.post.entity.Post;
import com.study.everytime.domain.post.entity.Scrap;
import com.study.everytime.domain.post.exception.PostException;
import com.study.everytime.domain.post.repository.LikeRepository;
import com.study.everytime.domain.post.repository.PostRepository;
import com.study.everytime.domain.post.repository.ScrapRepository;
import com.study.everytime.domain.user.entity.User;
import com.study.everytime.domain.user.exception.UserException;
import com.study.everytime.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final LikeRepository likeRepository;
    private final ScrapRepository scrapRepository;

    public void createPost(Long userId, Long boardId, CreatePostDto dto) {
        User user = getUser(userId);
        Board board = getBoard(boardId);

        log.info("title: {}, content: {}, isQuestion: {}, anonymous: {}", dto.title(), dto.content(), dto.question(), dto.anonymous());

        Post post = new Post(dto.title(), dto.content(), dto.question(), dto.anonymous(), user, board);
        postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public ReadPostDto readPost(Long userId, Long postId) {
        Post post = getPost(postId);
        List<Like> likes = likeRepository.findByPost_Id(postId);
        List<Scrap> scraps = scrapRepository.findByPost_Id(postId);
        return ReadPostDto.of(userId, post, likes, scraps);
    }

    @Transactional(readOnly = true)
    public Slice<BoardPostPageDto> readBoardPosts(Long boardId, Pageable pageable) {
        return postRepository.findByBoard_Id(boardId, pageable)
                .map(BoardPostPageDto::from);
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
            throw new PostException.QuestionNotDeletableException();
        }

        postRepository.delete(post);
    }

    public void addLike(Long userId, Long postId) {
        if (likeRepository.existsByUser_IdAndPost_Id(userId, postId)) {
            throw new PostException.LikeDuplicatedException();
        }

        User user = getUser(userId);
        Post post = getPost(postId);

        Like like = new Like(user, post);
        likeRepository.save(like);
    }

    public void addScrap(Long userId, Long postId) {
        if (scrapRepository.existsByUser_IdAndPost_Id(userId, postId)) {
            throw new PostException.ScrapDuplicatedException();
        }

        User user = getUser(userId);
        Post post = getPost(postId);

        Scrap scrap = new Scrap(user, post);
        scrapRepository.save(scrap);
    }

    public void cancelScrap(Long userId, Long postId) {
        Scrap scrap = scrapRepository.findByUser_IdAndPost_Id(userId, postId)
                .orElseThrow(PostException.ScrapNotFoundException::new);
        scrapRepository.delete(scrap);
    }

    @Transactional(readOnly = true)
    public Slice<MyPostPageDto> readMyPosts(Long userId, Pageable pageable) {
        return postRepository.findByWriter_id(userId, pageable)
                .map(MyPostPageDto::from);
    }

    @Transactional(readOnly = true)
    public Slice<MyPostPageDto> readMyScrabs(Long userId, Pageable pageable) {
        return postRepository.findByScrab_User_Id(userId, pageable)
                .map(MyPostPageDto::from);
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
