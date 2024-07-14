package com.study.everytime.domain.user.controller;

import com.study.everytime.domain.user.dto.UpdateUserDto;
import com.study.everytime.domain.user.dto.readUserDto;
import com.study.everytime.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public readUserDto readUser(@AuthenticationPrincipal Long userId) {
        return userService.readUser(userId);
    }

    @PatchMapping
    public void updateUser(@AuthenticationPrincipal Long userId, @RequestBody UpdateUserDto dto) {
        userService.updateUser(userId, dto);
    }

    @DeleteMapping
    public void deleteUser(@AuthenticationPrincipal Long userId) {
        userService.deleteUser(userId);
    }

}
