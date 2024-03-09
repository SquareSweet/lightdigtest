package me.sqsw.lightdigtest.service;

import lombok.RequiredArgsConstructor;
import me.sqsw.lightdigtest.dto.JwtRequest;
import me.sqsw.lightdigtest.dto.JwtResponse;
import me.sqsw.lightdigtest.utils.JwtTokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final JwtTokenUtils tokenUtils;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtResponse createAuthToken(JwtRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = tokenUtils.generateJwtToken(userDetails);

        return new JwtResponse(token);
    }
}
