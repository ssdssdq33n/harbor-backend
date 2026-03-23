package com.hungnv.backend.infrastructure.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class TokenBlacklistService {
    private final StringRedisTemplate template;

    public TokenBlacklistService(StringRedisTemplate template) {
        this.template = template;
    }

    public void blacklist(String token, long ttlSeconds) {
        template.opsForValue().set("blacklist:" + token, "1", Duration.ofSeconds(ttlSeconds));
    }

    public boolean isBlacklisted(String token) {
        return Boolean.TRUE.equals(template.hasKey("blacklist:" + token));
    }
}

