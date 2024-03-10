package me.sqsw.lightdigtest.mapper;

import lombok.RequiredArgsConstructor;
import me.sqsw.lightdigtest.dto.RequestCreateDto;
import me.sqsw.lightdigtest.dto.RequestFullDto;
import me.sqsw.lightdigtest.dto.RequestShortDto;
import me.sqsw.lightdigtest.model.Request;
import me.sqsw.lightdigtest.model.RequestState;
import me.sqsw.lightdigtest.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class RequestMapper {
    private final UserMapper userMapper;

    public Request toRequest(RequestCreateDto request, User user) {
        return Request.builder()
                .user(user)
                .title(request.getTitle())
                .text(request.getText())
                .state(RequestState.DRAFT)
                .phoneNumber(request.getPhoneNumber())
                .createdOn(LocalDateTime.now())
                .build();
    }

    public RequestFullDto toRequestFull(Request request) {
        return RequestFullDto.builder()
                .id(request.getId())
                .user(userMapper.toUserInfo(request.getUser()))
                .title(request.getTitle())
                .text(request.getText())
                .state(request.getState())
                .phoneNumber(request.getPhoneNumber())
                .createdOn(request.getCreatedOn())
                .sentOn(request.getSentOn())
                .build();
    }

    public RequestShortDto requestShort(Request request) {
        return RequestShortDto.builder()
                .id(request.getId())
                .username(request.getUser().getUsername())
                .title(request.getTitle())
                .state(request.getState())
                .createdOn(request.getCreatedOn())
                .sentOn(request.getSentOn())
                .build();
    }
}
