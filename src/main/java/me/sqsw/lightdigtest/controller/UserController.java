package me.sqsw.lightdigtest.controller;

import lombok.RequiredArgsConstructor;
import me.sqsw.lightdigtest.dto.RequestCreateDto;
import me.sqsw.lightdigtest.dto.RequestFullDto;
import me.sqsw.lightdigtest.service.RequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final RequestService requestService;

    @GetMapping("/requests/{requestId}")
    public RequestFullDto getRequest(@PathVariable Long requestId) {
        return requestService.getOwnRequest(requestId);
    }

    @GetMapping("/requests")
    public List<RequestFullDto> getRequest() {
        return requestService.getUserRequests();
    }

    @PostMapping("/requests/create")
    public RequestFullDto createRequest(@RequestBody RequestCreateDto request) {
        return requestService.create(request);
    }
}
