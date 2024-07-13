package com.study.everytime.domain.board.exception;

import com.study.everytime.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class BoardException extends BusinessException {

    private static final String DEFAULT_CODE_PREFIX = "Board";

    public BoardException(String codePrefix, int errorCode, HttpStatus httpStatus, String errorMessage) {
        super(codePrefix, errorCode, httpStatus, errorMessage);
    }

    public static final class BoardNotFoundException extends BusinessException {
        public BoardNotFoundException() {
            super(DEFAULT_CODE_PREFIX, 1, HttpStatus.BAD_REQUEST, "게시판을 찾을 수 없습니다.");
        }

        public BoardNotFoundException(String message) {
            super(DEFAULT_CODE_PREFIX, 1, HttpStatus.BAD_REQUEST, message);
        }
    }

    public static final class BoardAuthException extends BusinessException {
        public BoardAuthException() {
            super(DEFAULT_CODE_PREFIX, 2, HttpStatus.BAD_REQUEST, "권한이 없습니다.");
        }

        public BoardAuthException(String message) {
            super(DEFAULT_CODE_PREFIX, 2, HttpStatus.BAD_REQUEST, message);
        }
    }

}
