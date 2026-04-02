package com.ou.ailearning.service;

import com.ou.ailearning.config.JwtProperties;
import com.ou.ailearning.dto.response.AuthResponse;
import com.ou.ailearning.dto.request.LoginRequest;
import com.ou.ailearning.dto.request.RegisterRequest;
import com.ou.ailearning.dto.response.UserResponse;
import com.ou.ailearning.entity.User;
import com.ou.ailearning.entity.enums.Role;
import com.ou.ailearning.exeption.AccountDisabledException;
import com.ou.ailearning.exeption.DuplicateResourceException;
import com.ou.ailearning.exeption.ResourceNotFoundException;
import com.ou.ailearning.repository.UserRepository;
import com.ou.ailearning.security.JwtService;
import com.ou.ailearning.service.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserMapper userMapper;
    private final JwtProperties jwtProperties;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, UserDetailsService userDetailsService, UserMapper userMapper, JwtProperties jwtProperties) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userMapper = userMapper;
        this.jwtProperties = jwtProperties;
    }

    public UserResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new DuplicateResourceException("Username đã tồn tại");
        }
        if (userRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("Email đã tồn tại");
        }

        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.STUDENT)
                .build();

        user = userRepository.save(user);
        log.info("User registered: {}", user.getUsername());
        return userMapper.toResponse(user);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.usernameOrEmail())
                .or(() -> userRepository.findByEmail(request.usernameOrEmail()))
                .orElseThrow(()  -> new BadCredentialsException("Username/Email hoặc mật khẩu không đúng"));

        if (Boolean.FALSE.equals(user.getActive())) {
            throw new AccountDisabledException("Tài khoản đã bị vô hiệu hóa");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), request.password())
        );

        var userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String accessToken = jwtService.generateAccessToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        log.info("User logged in: {}", user.getUsername());

        return AuthResponse.of(
                accessToken,
                refreshToken,
                "Bearer",
                jwtProperties.getAccessTokenExpirationMs(),
                userMapper.toResponse(user)
        );
    }

    public UserResponse getCurrentUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: {}", username));
        return userMapper.toResponse(user);
    }
}
