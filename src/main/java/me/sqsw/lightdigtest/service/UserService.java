package me.sqsw.lightdigtest.service;

import me.sqsw.lightdigtest.model.User;

import java.util.List;

public interface UserService {
    User getUserById(Long userId);

    User getUserByUsername(String username);

    List<User> getAllUsers();

    User save(User user);
}
