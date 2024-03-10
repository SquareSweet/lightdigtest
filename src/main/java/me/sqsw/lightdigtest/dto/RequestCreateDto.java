package me.sqsw.lightdigtest.dto;

import lombok.Getter;
import lombok.Setter;
import me.sqsw.lightdigtest.model.RequestState;

@Getter
@Setter
public class RequestCreateDto {
    private String title;
    private String text;
    private RequestState state;
    private String phoneNumber;
}
