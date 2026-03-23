package com.hungnv.backend.modules.container.repository;

import com.hungnv.backend.modules.container.entity.ContainerStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContainerStatusHistoryRepository extends JpaRepository<ContainerStatusHistory, Integer> {
    List<ContainerStatusHistory> findByContainerIdOrderByCreatedAtDesc(String containerId);
}

