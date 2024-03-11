package me.sqsw.lightdigtest.service;

import me.sqsw.lightdigtest.dto.UserInfo;

import java.util.List;

public interface AdminService {
    List<UserInfo> getAllUsers();
    UserInfo getUser(Long userId);
    UserInfo grantOperatorPermissions(Long userId);
    UserInfo revokeOperatorPermissions(Long userId);
}
