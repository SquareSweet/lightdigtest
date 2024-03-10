package me.sqsw.lightdigtest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCreateDto {
    private String title;
    private String text;
    private String phoneNumber;
}
