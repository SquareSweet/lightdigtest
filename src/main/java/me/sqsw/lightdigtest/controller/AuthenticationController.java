package me.sqsw.lightdigtest.controller;

import lombok.RequiredArgsConstructor;
import me.sqsw.lightdigtest.dto.JwtRequest;
import me.sqsw.lightdigtest.dto.JwtResponse;
import me.sqsw.lightdigtest.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping
    public JwtResponse createAuthToken(@RequestBody JwtRequest authRequest) {
        return authenticationService.createAuthToken(authRequest);
    }
}
