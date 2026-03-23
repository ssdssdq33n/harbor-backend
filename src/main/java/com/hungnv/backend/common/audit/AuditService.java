package com.hungnv.backend.common.audit;

import com.hungnv.backend.modules.user.entity.SystemLog;
import com.hungnv.backend.modules.user.repository.SystemLogRepository;
import com.hungnv.backend.modules.user.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuditService {
    private final SystemLogRepository systemLogRepository;
    private final UserRepository userRepository;

    public AuditService(SystemLogRepository systemLogRepository, UserRepository userRepository) {
        this.systemLogRepository = systemLogRepository;
        this.userRepository = userRepository;
    }

    public void log(String action, String description) {
        Integer userId = resolveUserId();
        systemLogRepository.save(SystemLog.builder()
                .userId(userId)
                .action(action)
                .description(description)
                .createdAt(Instant.now())
                .build());
    }

    private Integer resolveUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getName() == null) return null;
        return userRepository.findByUsername(auth.getName()).map(u -> u.getUserId()).orElse(null);
    }
}

