package com.ou.ailearning.service;

import com.ou.ailearning.dto.UserResponse;
import com.ou.ailearning.entity.User;
import com.ou.ailearning.entity.enums.Role;
import com.ou.ailearning.exeption.ResourceNotFoundException;
import com.ou.ailearning.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional(readOnly = true)
    public Page<UserResponse> findAll(String search, Role role, Pageable pageable) {
        Page<User> users = userRepository.findAllBySearchAndRole(search, role, pageable);

        return users.map(userMapper::toResponse);
    }

    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
        return userMapper.toResponse(user);
    }
}
