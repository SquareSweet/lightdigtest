package me.sqsw.lightdigtest.controller;

import lombok.RequiredArgsConstructor;
import me.sqsw.lightdigtest.dto.RequestShortDto;
import me.sqsw.lightdigtest.dto.UserInfo;
import me.sqsw.lightdigtest.model.RequestState;
import me.sqsw.lightdigtest.service.AdminService;
import me.sqsw.lightdigtest.service.RequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final RequestService requestService;

    @GetMapping("users")
    public List<UserInfo> getAllUsers() {
        return adminService.getAllUsers();
    }

    @GetMapping("users/{userId}")
    public UserInfo getUser(@PathVariable Long userId) {
        return adminService.getUser(userId);
    }

    @PostMapping ("users/{userId}/grant_permissions")
    public UserInfo grantPermissions(@PathVariable Long userId) {
        return adminService.grantOperatorPermissions(userId);
    }

    @PostMapping ("users/{userId}/revoke_permissions")
    public UserInfo revokePermissions(@PathVariable Long userId) {
        return adminService.revokeOperatorPermissions(userId);
    }

    @GetMapping("/requests")
    public List<RequestShortDto> getUserRequests(@RequestParam(required = false) String username,
                                                 @RequestParam(defaultValue = "0") Integer page,
                                                 @RequestParam(required = false) String sort) {
        List<RequestState> states = List.of(RequestState.SENT, RequestState.ACCEPTED, RequestState.DENIED);
        return requestService.geAllRequests(username, states, page, sort);
    }
}
