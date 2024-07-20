package com.study.everytime.domain.post.exception;

import com.study.everytime.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class PostException extends BusinessException {

    private static final String DEFAULT_CODE_PREFIX = "POST";

    public PostException(String codePrefix, int errorCode, HttpStatus httpStatus, String errorMessage) {
        super(codePrefix, errorCode, httpStatus, errorMessage);
    }

    public static final class PostNotFoundException extends PostException {
        public PostNotFoundException() {
            super(DEFAULT_CODE_PREFIX, 1, HttpStatus.BAD_REQUEST, "게시글을 찾을 수 없습니다.");
        }

        public PostNotFoundException(String message) {
            super(DEFAULT_CODE_PREFIX, 1, HttpStatus.BAD_REQUEST, message);
        }
    }

    public static final class PostAuthException extends PostException {
        public PostAuthException() {
            super(DEFAULT_CODE_PREFIX, 2, HttpStatus.BAD_REQUEST, "권한이 없습니다.");
        }

        public PostAuthException(String message) {
            super(DEFAULT_CODE_PREFIX, 2, HttpStatus.BAD_REQUEST, message);
        }
    }

    public static final class PostQuestionDeleteException extends PostException {
        public PostQuestionDeleteException() {
            super(DEFAULT_CODE_PREFIX, 3, HttpStatus.BAD_REQUEST, "질문글은 삭제할 수 없습니다.");
        }

        public PostQuestionDeleteException(String message) {
            super(DEFAULT_CODE_PREFIX, 3, HttpStatus.BAD_REQUEST, message);
        }
    }

    public static final class LikeDuplicateException extends PostException {
        public LikeDuplicateException() {
            super(DEFAULT_CODE_PREFIX, 4, HttpStatus.BAD_REQUEST, "이미 공감한 글입니다.");
        }

        public LikeDuplicateException(String message) {
            super(DEFAULT_CODE_PREFIX, 4, HttpStatus.BAD_REQUEST, message);
        }
    }

    public static final class ScrapDuplicatedException extends PostException {
        public ScrapDuplicatedException() {
            super(DEFAULT_CODE_PREFIX, 5, HttpStatus.BAD_REQUEST, "이미 스크랩한 글입니다.");
        }

        public ScrapDuplicatedException(String message) {
            super(DEFAULT_CODE_PREFIX, 5, HttpStatus.BAD_REQUEST, message);
        }
    }

    public static final class ScrapNotFoundException extends PostException {

        public ScrapNotFoundException() {
            super(DEFAULT_CODE_PREFIX, 6, HttpStatus.BAD_REQUEST, "스크랩을 찾을 수 없습니다.");
        }

        public ScrapNotFoundException(String message) {
            super(DEFAULT_CODE_PREFIX, 6, HttpStatus.BAD_REQUEST, message);
        }
    }

}
