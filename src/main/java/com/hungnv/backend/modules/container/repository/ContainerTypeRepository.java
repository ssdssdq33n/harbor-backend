package com.hungnv.backend.modules.container.repository;

import com.hungnv.backend.modules.container.entity.ContainerType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContainerTypeRepository extends JpaRepository<ContainerType, Integer> {
}

