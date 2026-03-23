package com.hungnv.backend.modules.yard.repository;

import com.hungnv.backend.modules.yard.entity.ContainerPosition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContainerPositionRepository extends JpaRepository<ContainerPosition, Integer> {
    Optional<ContainerPosition> findByContainerId(String containerId);
    Optional<ContainerPosition> findBySlotIdAndTier(Integer slotId, Integer tier);
    List<ContainerPosition> findBySlotId(Integer slotId);
}

