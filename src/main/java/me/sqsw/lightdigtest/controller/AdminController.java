package me.sqsw.lightdigtest.controller;

import lombok.RequiredArgsConstructor;
import me.sqsw.lightdigtest.dto.UserInfo;
import me.sqsw.lightdigtest.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("users")
    public List<UserInfo> getAllUsers() {
        return adminService.getAllUsers();
    }

    @GetMapping("users/{userId}")
    public UserInfo getAllUsers(@PathVariable Long userId) {
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
}
