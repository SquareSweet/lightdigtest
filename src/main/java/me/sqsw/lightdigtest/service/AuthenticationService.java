package me.sqsw.lightdigtest.service;

import me.sqsw.lightdigtest.dto.JwtRequest;
import me.sqsw.lightdigtest.dto.JwtResponse;

public interface AuthenticationService {
    JwtResponse createAuthToken(JwtRequest authRequest);
}
