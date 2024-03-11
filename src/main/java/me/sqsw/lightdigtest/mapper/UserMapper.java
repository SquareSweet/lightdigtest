package me.sqsw.lightdigtest.mapper;

import me.sqsw.lightdigtest.dto.UserInfo;
import me.sqsw.lightdigtest.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserInfo toUserInfo(User user) {
        return UserInfo.builder()
                .id(user.getId())
                .username(user.getUsername())
                .roles(user.getRoles())
                .build();
    }
}
