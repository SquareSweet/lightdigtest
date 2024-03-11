package me.sqsw.lightdigtest.controller;

import lombok.RequiredArgsConstructor;
import me.sqsw.lightdigtest.dto.JwtRequest;
import me.sqsw.lightdigtest.dto.JwtResponse;
import me.sqsw.lightdigtest.dto.RefreshJwtRequest;
import me.sqsw.lightdigtest.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public JwtResponse login(@RequestBody JwtRequest authRequest) {
        return authenticationService.login(authRequest);
    }

    @PostMapping("/token")
    public JwtResponse createAccessToken(@RequestBody RefreshJwtRequest authRequest) {
        return authenticationService.createAccessToken(authRequest.getRefreshToken());
    }

    @PostMapping("/refresh")
    public JwtResponse createRefreshToken(@RequestBody RefreshJwtRequest authRequest) {
        return authenticationService.createRefreshToken(authRequest.getRefreshToken());
    }

    @PostMapping("/logout")
    public void logout(@RequestBody RefreshJwtRequest authRequest) {
        authenticationService.logout(authRequest.getRefreshToken());
    }
}
