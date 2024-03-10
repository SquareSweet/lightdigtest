package me.sqsw.lightdigtest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCreateDto {
    @NotBlank
    private String title;
    @NotBlank
    private String text;
    @NotBlank
    private String phoneNumber;
}
