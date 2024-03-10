package me.sqsw.lightdigtest.service;

import lombok.RequiredArgsConstructor;
import me.sqsw.lightdigtest.dto.UserInfo;
import me.sqsw.lightdigtest.exception.RoleNotFoundException;
import me.sqsw.lightdigtest.exception.UserNotFoundException;
import me.sqsw.lightdigtest.mapper.UserMapper;
import me.sqsw.lightdigtest.model.Role;
import me.sqsw.lightdigtest.model.User;
import me.sqsw.lightdigtest.repository.RoleRepository;
import me.sqsw.lightdigtest.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserInfo> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserInfo).collect(Collectors.toList());
    }

    @Override
    public UserInfo getUser(Long userId) {
        return userMapper.toUserInfo(userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId)));
    }

    @Override
    public UserInfo grantOperatorPermissions(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Role role = roleRepository.findByName("ROLE_OPERATOR").orElseThrow(() -> new RoleNotFoundException("ROLE_OPERATOR"));
        user.getRoles().add(role);
        user = userRepository.save(user);
        return userMapper.toUserInfo(user);
    }

    @Override
    public UserInfo revokeOperatorPermissions(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Role role = roleRepository.findByName("ROLE_OPERATOR").orElseThrow(() -> new RoleNotFoundException("ROLE_OPERATOR"));
        user.getRoles().remove(role);
        user = userRepository.save(user);
        return userMapper.toUserInfo(user);
    }
}