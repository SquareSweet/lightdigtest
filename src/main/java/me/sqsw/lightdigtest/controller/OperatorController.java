package me.sqsw.lightdigtest.controller;

import lombok.RequiredArgsConstructor;
import me.sqsw.lightdigtest.dto.RequestFullDto;
import me.sqsw.lightdigtest.model.RequestState;
import me.sqsw.lightdigtest.service.RequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/oper")
@RequiredArgsConstructor
public class OperatorController {
    private final RequestService requestService;

    @GetMapping("/requests")
    public List<RequestFullDto> getUserRequests(@RequestParam(required = false) String username,
                                                @RequestParam(defaultValue = "0") Integer page,
                                                @RequestParam(required = false) String sort) {
        return requestService.geAllRequests(username, List.of(RequestState.SENT), page, sort);
    }

    @GetMapping("/requests/{requestId}")
    public RequestFullDto getRequest(@PathVariable Long requestId) {
        return requestService.getRequest(requestId);
    }

    @PostMapping("/requests/{requestId}/accept")
    public RequestFullDto acceptRequest(@PathVariable Long requestId) {
        return requestService.accept(requestId);
    }

    @PostMapping("/requests/{requestId}/deny")
    public RequestFullDto denyRequest(@PathVariable Long requestId) {
        return requestService.deny(requestId);
    }
}
