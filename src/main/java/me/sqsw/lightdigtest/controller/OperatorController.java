package me.sqsw.lightdigtest.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import me.sqsw.lightdigtest.dto.RequestFullDto;
import me.sqsw.lightdigtest.dto.RequestShortDto;
import me.sqsw.lightdigtest.model.RequestState;
import me.sqsw.lightdigtest.service.RequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/oper")
@RequiredArgsConstructor
public class OperatorController {
    private final RequestService requestService;

    @Operation(summary = "Get a list of all requests")
    @GetMapping("/requests")
    public List<RequestShortDto> getUserRequests(@RequestParam(required = false) String username,
                                                 @RequestParam(defaultValue = "0") Integer page,
                                                 @RequestParam(required = false) String sort) {
        return requestService.geAllRequests(username, List.of(RequestState.SENT), page, sort);
    }

    @Operation(summary = "Get request by its id")
    @GetMapping("/requests/{requestId}")
    public RequestFullDto getRequest(@PathVariable Long requestId) {
        return requestService.getRequest(requestId);
    }

    @Operation(summary = "Accept request by its id")
    @PostMapping("/requests/{requestId}/accept")
    public RequestFullDto acceptRequest(@PathVariable Long requestId) {
        return requestService.accept(requestId);
    }

    @Operation(summary = "Deny request by its id")
    @PostMapping("/requests/{requestId}/deny")
    public RequestFullDto denyRequest(@PathVariable Long requestId) {
        return requestService.deny(requestId);
    }
}
