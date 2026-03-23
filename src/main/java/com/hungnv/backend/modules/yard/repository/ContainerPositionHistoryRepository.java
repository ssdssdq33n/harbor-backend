package com.hungnv.backend.modules.yard.repository;

import com.hungnv.backend.modules.yard.entity.ContainerPositionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContainerPositionHistoryRepository extends JpaRepository<ContainerPositionHistory, Integer> {
    List<ContainerPositionHistory> findByContainerIdOrderByMovedAtDesc(String containerId);
}

