package com.ou.ailearning.service;

import com.ou.ailearning.dto.request.UpdateUserRequest;
import com.ou.ailearning.dto.response.UserResponse;
import com.ou.ailearning.entity.User;
import com.ou.ailearning.entity.enums.Role;
import com.ou.ailearning.exeption.DuplicateResourceException;
import com.ou.ailearning.exeption.ResourceNotFoundException;
import com.ou.ailearning.repository.UserRepository;
import com.ou.ailearning.service.mapper.UserMapper;
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
    @Transactional
    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
        return userMapper.toResponse(user);
    }

    public UserResponse update(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));

        if (request.email() != null &&
                userRepository.existsByEmail(request.email()) &&
                !request.email().equals(user.getEmail())) {
            throw new DuplicateResourceException("Email đã tồn tại");
        }

        if (request.email() != null) {
            user.setEmail(request.email());
        }

        if (request.password() != null && !request.password().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.password()));
        }

        if (request.role() != null) {
            user.setRole(request.role());
        }

        if (request.avatar() != null) {
            user.setAvatar(request.avatar());
        }

        if (request.active() != null) {
            user.setActive(request.active());
        }

        User updateUser = userRepository.save(user);
        return userMapper.toResponse(updateUser);
    }
    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));

        if (Boolean.FALSE.equals(user.getActive())) {
            throw new IllegalStateException("User đã bị vô hiệu hóa trước đó");
        }

        user.setActive(false);
        userRepository.save(user);
    }

    @Transactional
    public void restore(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));

        user.setActive(true);
        userRepository.save(user);
    }

}
