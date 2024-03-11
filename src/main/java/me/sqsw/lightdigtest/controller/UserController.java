package me.sqsw.lightdigtest.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.sqsw.lightdigtest.dto.RequestCreateDto;
import me.sqsw.lightdigtest.dto.RequestFullDto;
import me.sqsw.lightdigtest.dto.RequestShortDto;
import me.sqsw.lightdigtest.service.RequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final RequestService requestService;

    @Operation(summary = "Get own request by its id")
    @GetMapping("/requests/{requestId}")
    public RequestFullDto getRequest(@PathVariable Long requestId) {
        return requestService.getOwnRequest(requestId);
    }

    @Operation(summary = "Get a list of all own requests")
    @GetMapping("/requests")
    public List<RequestShortDto> getRequests(@RequestParam(defaultValue = "0") Integer page,
                                             @RequestParam(required = false) String sort) {
        return requestService.getUserOwnRequests(page, sort);
    }

    @Operation(summary = "Create a new request")
    @PostMapping("/requests/create")
    public RequestFullDto createRequest(@RequestBody @Valid RequestCreateDto request) {
        return requestService.create(request);
    }

    @Operation(summary = "Edit an existing request")
    @PostMapping("/requests/{requestId}/edit")
    public RequestFullDto editRequest(@RequestBody RequestCreateDto request, @PathVariable Long requestId) {
        return requestService.edit(request, requestId);
    }

    @Operation(summary = "Send an existing request for moderation")
    @PostMapping("/requests/{requestId}/send")
    public RequestFullDto createRequest(@PathVariable Long requestId) {
        return requestService.send(requestId);
    }
}
