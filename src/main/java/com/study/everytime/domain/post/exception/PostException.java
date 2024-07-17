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

}