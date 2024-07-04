package com.study.everytime.global.auth.exception;

import com.study.everytime.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public sealed class AuthException extends BusinessException {

    private static final String DEFAULT_CODE_PREFIX = "AUTH";

    public AuthException(String codePrefix, int errorCode, HttpStatus httpStatus, String errorMessage) {
        super(codePrefix, errorCode, httpStatus, errorMessage);
    }

    public static final class NotJoinedUserException extends AuthException {
        public NotJoinedUserException() {
            super(DEFAULT_CODE_PREFIX, 1, HttpStatus.BAD_REQUEST, "가입하지 않은 사용자입니다.");
        }

        public NotJoinedUserException(String message) {
            super(DEFAULT_CODE_PREFIX, 1, HttpStatus.BAD_REQUEST, message);
        }
    }

    public static final class AlreadyJoinedUserException extends AuthException {
        public AlreadyJoinedUserException() {
            super(DEFAULT_CODE_PREFIX, 3, HttpStatus.BAD_REQUEST, "이미 가입한 사용자입니다.");
        }

        public AlreadyJoinedUserException(String message) {
            super(DEFAULT_CODE_PREFIX, 3, HttpStatus.BAD_REQUEST, message);
        }
    }

    public static final class InvalidTokenException extends AuthException {
        public InvalidTokenException() {
            super(DEFAULT_CODE_PREFIX, 2, HttpStatus.BAD_REQUEST, "유효하지 않은 토큰입니다.");
        }

        public InvalidTokenException(String message) {
            super(DEFAULT_CODE_PREFIX, 2, HttpStatus.BAD_REQUEST, message);
        }
    }

}
