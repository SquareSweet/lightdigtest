package me.sqsw.lightdigtest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.sqsw.lightdigtest.model.Role;

import java.util.Set;

@Getter
@Setter
@Builder
public class UserInfo {
    private Long id;
    private String username;
    private Set<Role> roles;
}
