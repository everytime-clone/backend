package com.study.everytime.global.auth.controller;

import com.study.everytime.global.auth.dto.ReissueDto;
import com.study.everytime.global.auth.dto.SignInDto;
import com.study.everytime.global.auth.dto.SignUpDto;
import com.study.everytime.global.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody SignUpDto.Request request) {
        authService.join(request);
    }

    @PostMapping("/sign-in")
    public SignInDto.Response signIn(@RequestBody SignInDto.Request request) {
        return authService.issueToken(request);
    }

    @PostMapping("/reissue")
    public ReissueDto.Response reissue(@RequestBody ReissueDto.Request request) {
        return authService.reissueToken(request);
    }

}
