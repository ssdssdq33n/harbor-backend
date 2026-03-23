package com.hungnv.backend.modules.container.repository;

import com.hungnv.backend.modules.container.entity.Container;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContainerRepository extends JpaRepository<Container, String> {
    java.util.List<Container> findByStatusId(Integer statusId);
    java.util.List<Container> findByManifestId(Integer manifestId);
    java.util.List<Container> findByCargoTypeId(Integer cargoTypeId);
}
