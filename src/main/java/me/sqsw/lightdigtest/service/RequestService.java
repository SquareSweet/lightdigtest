package me.sqsw.lightdigtest.service;

import me.sqsw.lightdigtest.dto.RequestCreateDto;
import me.sqsw.lightdigtest.dto.RequestFullDto;
import me.sqsw.lightdigtest.model.RequestState;

import java.util.List;

public interface RequestService {
    RequestFullDto create(RequestCreateDto request);

    List<RequestFullDto> geAllRequests(String username, List<RequestState> states, Integer page, String sort);

    RequestFullDto getOwnRequest(Long requestId);

    List<RequestFullDto> getUserOwnRequests(Integer page, String sort);

    RequestFullDto getRequest(Long requestId);

    RequestFullDto edit(RequestCreateDto request, Long requestId);

    RequestFullDto send(Long requestId);

    RequestFullDto accept(Long requestId);

    RequestFullDto deny(Long requestId);

}
