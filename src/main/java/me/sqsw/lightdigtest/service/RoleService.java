package me.sqsw.lightdigtest.service;

import me.sqsw.lightdigtest.model.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(String name);
}
