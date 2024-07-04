package com.study.everytime.domain.user.exception;

import com.study.everytime.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class UserException extends BusinessException {

    private static final String DEFAULT_CODE_PREFIX = "USER";

    public UserException(String codePrefix, int errorCode, HttpStatus httpStatus, String errorMessage) {
        super(codePrefix, errorCode, httpStatus, errorMessage);
    }

    public static final class UserNotFoundException extends UserException {
        public UserNotFoundException() {
            super(DEFAULT_CODE_PREFIX, 1, HttpStatus.BAD_REQUEST, "사용자를 찾을 수 없습니다.");
        }

        public UserNotFoundException(String message) {
            super(DEFAULT_CODE_PREFIX, 1, HttpStatus.BAD_REQUEST, message);
        }
    }
    
}
