package me.sqsw.lightdigtest.service;

import lombok.RequiredArgsConstructor;
import me.sqsw.lightdigtest.model.Role;
import me.sqsw.lightdigtest.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }
}
