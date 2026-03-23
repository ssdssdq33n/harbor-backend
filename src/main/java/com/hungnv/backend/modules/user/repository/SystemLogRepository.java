package com.hungnv.backend.modules.user.repository;

import com.hungnv.backend.modules.user.entity.SystemLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SystemLogRepository extends JpaRepository<SystemLog, Integer> {
    List<SystemLog> findByUserIdOrderByCreatedAtDesc(Integer userId);
}

