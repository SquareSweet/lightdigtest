package me.sqsw.lightdigtest.service;

import lombok.RequiredArgsConstructor;
import me.sqsw.lightdigtest.dto.UserInfo;
import me.sqsw.lightdigtest.exception.RoleNotFoundException;
import me.sqsw.lightdigtest.mapper.UserMapper;
import me.sqsw.lightdigtest.model.Role;
import me.sqsw.lightdigtest.model.User;
import me.sqsw.lightdigtest.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserInfo> getAllUsers() {
        return userService.getAllUsers().stream().map(userMapper::toUserInfo).collect(Collectors.toList());
    }

    @Override
    public UserInfo getUser(Long userId) {
        return userMapper.toUserInfo(userService.getUserById(userId)); //throws exception if not found
    }

    @Override
    @Transactional
    public UserInfo grantOperatorPermissions(Long userId) {
        User user = userService.getUserById(userId); //throws exception if not found
        Role role = roleRepository.findByName("ROLE_OPERATOR").orElseThrow(() -> new RoleNotFoundException("ROLE_OPERATOR"));
        user.getRoles().add(role);
        user = userService.save(user);
        return userMapper.toUserInfo(user);
    }

    @Override
    @Transactional
    public UserInfo revokeOperatorPermissions(Long userId) {
        User user = userService.getUserById(userId); //throws exception if not found
        Role role = roleRepository.findByName("ROLE_OPERATOR").orElseThrow(() -> new RoleNotFoundException("ROLE_OPERATOR"));
        user.getRoles().remove(role);
        user = userService.save(user);
        return userMapper.toUserInfo(user);
    }
}