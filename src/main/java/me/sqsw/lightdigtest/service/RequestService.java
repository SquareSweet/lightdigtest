package me.sqsw.lightdigtest.service;

import me.sqsw.lightdigtest.dto.RequestCreateDto;
import me.sqsw.lightdigtest.dto.RequestFullDto;

import java.util.List;

public interface RequestService {
    RequestFullDto create(RequestCreateDto request);

    RequestFullDto getOwnRequest(Long requestId);

    List<RequestFullDto> getUserRequests();
}
