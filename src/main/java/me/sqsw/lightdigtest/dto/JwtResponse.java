package me.sqsw.lightdigtest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {
    String accessToken;
    String refreshToken;
}
