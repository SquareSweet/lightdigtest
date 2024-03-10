package me.sqsw.lightdigtest.service;

import me.sqsw.lightdigtest.dto.JwtRequest;
import me.sqsw.lightdigtest.dto.JwtResponse;

public interface AuthenticationService {
    JwtResponse login(JwtRequest authRequest);
    JwtResponse createAccessToken(String refreshToken);
    JwtResponse createRefreshToken(String refreshToken);
    void logout(String refreshToken);
}
