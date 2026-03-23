package com.hungnv.backend.modules.auth.service.impl;

import com.hungnv.backend.common.security.jwt.JwtTokenProvider;
import com.hungnv.backend.infrastructure.mail.MailService;
import com.hungnv.backend.infrastructure.redis.TokenBlacklistService;
import com.hungnv.backend.modules.auth.dto.request.LoginRequest;
import com.hungnv.backend.modules.auth.dto.request.RegisterRequest;
import com.hungnv.backend.modules.auth.service.AuthService;
import com.hungnv.backend.modules.user.entity.Role;
import com.hungnv.backend.modules.user.entity.User;
import com.hungnv.backend.modules.user.repository.UserRepository;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.time.Duration;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final MailService mailService;
    private final StringRedisTemplate redisTemplate;
    private final TokenBlacklistService tokenBlacklistService;

    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtTokenProvider tokenProvider,
            MailService mailService,
            StringRedisTemplate redisTemplate,
            TokenBlacklistService tokenBlacklistService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.mailService = mailService;
        this.redisTemplate = redisTemplate;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    @Override
    public String login(LoginRequest request) {
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        if (user.getStatus() == null || user.getStatus() != 1) throw new RuntimeException("User disabled");
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) throw new RuntimeException("Invalid credentials");
        Map<String, Object> claims = new HashMap<>();
        claims.put("uid", user.getUserId());
        claims.put("roles", user.getRoles().stream().map(Role::getRoleName).toList());
        return tokenProvider.generateToken(user.getUsername(), claims);
    }

    @Override
    @Transactional
    public void register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) throw new RuntimeException("Username exists");
        if (request.getEmail() != null && userRepository.existsByEmail(request.getEmail())) throw new RuntimeException("Email exists");
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .status((short)1)
                .roles(new HashSet<>())
                .build();
        userRepository.save(user);
    }

    @Override
    public void forgotPassword(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Email not found"));
        if (user.getStatus() == null || user.getStatus() != 1) throw new RuntimeException("User disabled");
        String token = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set("pwdreset:" + token, user.getUsername(), Duration.ofMinutes(15));
        mailService.send(email, "Reset password", "Reset token: " + token);
    }

    @Override
    @Transactional
    public void resetPassword(String token, String newPassword) {
        String username = redisTemplate.opsForValue().get("pwdreset:" + token);
        if (username == null || username.isBlank()) throw new RuntimeException("Invalid token");
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        redisTemplate.delete("pwdreset:" + token);
    }

    @Override
    public void logout(String token) {
        long ttlSeconds = Math.max(0, tokenProvider.getExpiration(token).getEpochSecond() - java.time.Instant.now().getEpochSecond());
        tokenBlacklistService.blacklist(token, ttlSeconds);
    }
}
