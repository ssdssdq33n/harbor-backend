package com.hungnv.backend.modules.container.repository;

import com.hungnv.backend.modules.container.entity.ExportPriority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExportPriorityRepository extends JpaRepository<ExportPriority, Integer> {
    Optional<ExportPriority> findByContainerId(String containerId);
}

