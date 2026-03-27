package com.ou.ailearning.service;

import com.ou.ailearning.dto.UserResponse;
import com.ou.ailearning.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse toResponse(User user) {
        if(user == null) return null;
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getAvatar(),
                user.getCreatedAt()
        );
    }
}
