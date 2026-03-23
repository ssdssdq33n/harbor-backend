package com.hungnv.backend.modules.container.repository;

import com.hungnv.backend.modules.container.entity.ContainerAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContainerAttributeRepository extends JpaRepository<ContainerAttribute, ContainerAttribute.ContainerAttributeId> {
    List<ContainerAttribute> findByIdContainerId(String containerId);
}

