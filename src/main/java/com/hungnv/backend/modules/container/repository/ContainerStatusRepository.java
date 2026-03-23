package com.hungnv.backend.modules.container.repository;

import com.hungnv.backend.modules.container.entity.ContainerStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContainerStatusRepository extends JpaRepository<ContainerStatus, Integer> {
}

