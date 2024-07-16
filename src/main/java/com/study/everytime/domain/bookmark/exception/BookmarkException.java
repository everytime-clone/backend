package com.study.everytime.domain.bookmark.exception;

import com.study.everytime.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class BookmarkException extends BusinessException {

    private static final String DEFAULT_CODE_PREFIX = "BOOKMARK";

    public BookmarkException(String codePrefix, int errorCode, HttpStatus httpStatus, String errorMessage) {
        super(codePrefix, errorCode, httpStatus, errorMessage);
    }

    public static final class BookmarkNotFoundException extends BookmarkException {
        public BookmarkNotFoundException() {
            super(DEFAULT_CODE_PREFIX, 1, HttpStatus.BAD_REQUEST, "즐겨찾기를 찾을 수 없습니다.");
        }

        public BookmarkNotFoundException(String message) {
            super(DEFAULT_CODE_PREFIX, 1, HttpStatus.BAD_REQUEST, message);
        }
    }

}
