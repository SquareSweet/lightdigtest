package me.sqsw.lightdigtest.service;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import me.sqsw.lightdigtest.dto.JwtRequest;
import me.sqsw.lightdigtest.dto.JwtResponse;
import me.sqsw.lightdigtest.utils.JwtTokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtils tokenUtils;
    private final AuthenticationManager authenticationManager;

    private final TokenBlacklistingService blacklistingService;

    @Override
    public JwtResponse login(JwtRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        String accessToken = tokenUtils.generateAccessToken(userDetails);
        String refreshToken = tokenUtils.generateRefreshToken(userDetails);
        return new JwtResponse(accessToken, refreshToken);
    }

    @Override
    public JwtResponse createAccessToken(String refreshToken) {
        if (tokenUtils.validateRefreshToken(refreshToken) && blacklistingService.getTokenBlacklist(refreshToken) == null) {
            Claims claims = tokenUtils.getRefreshClaims(refreshToken);
            UserDetails userDetails = userDetailsService.loadUserByUsername(claims.getSubject());
            String accessToken = tokenUtils.generateAccessToken(userDetails);
            return new JwtResponse(accessToken, null);
        }
        return new JwtResponse(null, null);
    }

    @Override
    public JwtResponse createRefreshToken(String refreshToken) {
        if (tokenUtils.validateRefreshToken(refreshToken) && blacklistingService.getTokenBlacklist(refreshToken) == null) {
            final Claims claims = tokenUtils.getRefreshClaims(refreshToken);
            UserDetails userDetails = userDetailsService.loadUserByUsername(claims.getSubject());
            String accessToken = tokenUtils.generateAccessToken(userDetails);
            String newRefreshToken = tokenUtils.generateRefreshToken(userDetails);
            return new JwtResponse(accessToken, newRefreshToken);
        }
        throw new RuntimeException("Invalid token");
    }

    @Override
    public void logout(String refreshToken) {
        if (tokenUtils.validateRefreshToken(refreshToken) && blacklistingService.getTokenBlacklist(refreshToken) == null) {
            blacklistingService.blacklistToken(refreshToken);
        }
    }
}
