package me.sqsw.lightdigtest.service;

import me.sqsw.lightdigtest.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<User> findByUsername(String username);
}
