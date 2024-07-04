package com.study.everytime.domain.user.controller;

import com.study.everytime.domain.user.dto.UserInfo;
import com.study.everytime.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/userInfo")
    public UserInfo.Response userInfo(@AuthenticationPrincipal Long userId) {
        return userService.getUser(userId);
    }

}
